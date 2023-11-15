package com.ssafy.help.match.socket.service.impl;

import com.ssafy.help.match.db.entity.HelpEntity;
import com.ssafy.help.match.db.entity.HelpMatchStatus;
import com.ssafy.help.match.db.entity.HelpMatchType;
import com.ssafy.help.match.db.entity.SendMatchEntity;
import com.ssafy.help.match.db.repository.*;
import com.ssafy.help.match.event.dto.HelperSearchEventDTO;
import com.ssafy.help.match.event.emitter.EventEmitter;
import com.ssafy.help.match.event.emitter.EventTopicPrefix;
import com.ssafy.help.match.event.producer.KafkaEventProducer;
import com.ssafy.help.match.event.producer.KafkaEventTopic;
import com.ssafy.help.match.socket.dto.MatchPopEventDTO;
import com.ssafy.help.match.socket.dto.MatchPushEventDTO;
import com.ssafy.help.match.socket.dto.StatusChangeEventDTO;
import com.ssafy.help.match.socket.mapper.MatchEntityMapper;
import com.ssafy.help.match.socket.request.HelpMatchRequest;
import com.ssafy.help.match.socket.response.MatchStatusResponse;
import com.ssafy.help.match.socket.response.OtherMemberPoint;
import com.ssafy.help.match.socket.response.PushMatchItem;
import com.ssafy.help.match.socket.service.HelpMatchService;
import com.ssafy.help.match.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class HelpMatchServiceImpl implements HelpMatchService {

    @Value("${redis.topic.match-push-event.prefix}")
    public String MATCH_PUSH_EVENT_TOPIC_PREFIX;

    @Value("${redis.topic.match-pop-event.prefix}")
    public String MATCH_POP_EVENT_TOPIC_PREFIX;

    @Value("${redis.topic.status-change-event.prefix}")
    public String STATUS_CHANGE_EVENT_TOPIC_PREFIX;

    private final MemberSessionEntityRepository memberSessionEntityRepository;
    private final MemberPointRepository memberPointRepository;
    private final SendMemberIdSetRepository sendMemberIdSetRepository;
    private final SendMatchEntityRepository sendMatchEntityRepository;
    private final RedisTemplate<String,String> redisTemplate;
    private final ObjectSerializer objectSerializer;
    private final double[] maxDistanceList = {500d,1000d,1500d};
    private final HelpEntityRepository helpEntityRepository;
    private final EventEmitter eventEmitter;
    private final KafkaEventProducer kafkaEventProducer;

    @Override
    public void cancel(Long memberId) {
        if(!memberSessionEntityRepository.isOnMatchProgress(memberId)) return;

        memberSessionEntityRepository.setMatchStatus(memberId,HelpMatchStatus.DEFAULT);
        memberSessionEntityRepository.setMatchType(memberId,HelpMatchType.NONE);
        sendMatchEntityRepository.getAndDeleteByMemberId(memberId);
        emitStatusChangeEvent(memberId);

        sendMatchEntityRepository.getAndDeleteReceiveMemberIdSet(memberId)
                .forEach((receiveMemberId)-> emitMatchPopEvent(memberId,receiveMemberId));
    }

    @Override
    public MatchStatusResponse getStatus(Long memberId) {
        HelpMatchType helpMatchType = memberSessionEntityRepository.getMatchType(memberId);
        HelpMatchStatus helpMatchStatus = memberSessionEntityRepository.getMatchStatus(memberId);
        HelpEntity helpEntity = null;
        OtherMemberPoint otherMemberPoint = null;

        if (helpMatchStatus.equals(HelpMatchStatus.WAIT_COMPLETE)||helpMatchStatus.equals(HelpMatchStatus.ON_MOVE)){
            helpEntity = helpEntityRepository.findByMemberId(memberId);

            if(memberSessionEntityRepository.isOnMove(helpEntity.getOtherMemberId())){
                Point point = memberPointRepository.find(helpEntity.getOtherMemberId());
                otherMemberPoint = OtherMemberPoint
                        .builder()
                        .longitude(point.getX())
                        .latitude(point.getY())
                        .build();
            }
        }

        MatchStatusResponse response = MatchStatusResponse
                .builder()
                .helpMatchStatus(helpMatchStatus)
                .helpMatchType(helpMatchType)
                .data("helpEntity",helpEntity)
                .data("otherMemberPoint",otherMemberPoint)
                .build();

        return response;
    }

    @Override
    public List<PushMatchItem> list(Long memberId) {
        List<Long> matchMemberIdList = new ArrayList<>(sendMemberIdSetRepository.getSet(memberId));
        List<PushMatchItem> pushMatchItemList = new ArrayList<>();

        MatchEntityMapper mapper = MatchEntityMapper.INSTANCE;

        for (Long matchMemberId:matchMemberIdList){
            Optional.ofNullable(sendMatchEntityRepository.findByMemberId(matchMemberId))
                    .ifPresent((sendMatchEntity) -> {
                        Double distance = memberPointRepository.getDistance(memberId,matchMemberId);
                        pushMatchItemList.add(mapper.toItem(sendMatchEntity,distance));
                    });
        }

        return pushMatchItemList;
    }

    @Override
    public void match(Long memberId) {
        // ToDo 장애 여부 체크
//        saveAndChangeStatus(helpMatchRequest);
        SendMatchEntity sendMatchEntity = sendMatchEntityRepository.findByMemberId(memberId);

        Set<Long> receiveMemberIdSet = new HashSet<>();
        Point centerPoint = new Point(sendMatchEntity.getLongitude(),sendMatchEntity.getLatitude());

        for(double maxDistance:maxDistanceList){
            matchInRange(sendMatchEntity.getMemberId(), centerPoint, maxDistance, receiveMemberIdSet);
        }

        sendMatchEntityRepository.saveReceiveMemberIdSet(sendMatchEntity.getMemberId(), receiveMemberIdSet);
        if(receiveMemberIdSet.isEmpty()){
            eventEmitter.emit(EventTopicPrefix.HELPER_SEARCH, HelperSearchEventDTO.builder().memberId(memberId).metric(-1d).build());
        }
    }

    @Override
    public HelpMatchStatus getSimpStatus(Long memberId) {
        return memberSessionEntityRepository.getMatchStatus(memberId);
    }

    @Override
    public void saveAndChangeStatus(HelpMatchRequest helpMatchRequest) {
        StatusChangeEventDTO statusChangeEventDTO = StatusChangeEventDTO
                .builder()
                .memberId(helpMatchRequest.getMemberId())
                .helpMatchStatus(HelpMatchStatus.ON_MATCH_PROGRESS)
                .helpMatchType(HelpMatchType.SINGLE)
                .build();

        memberSessionEntityRepository.setMatchStatus(helpMatchRequest.getMemberId(),statusChangeEventDTO.getHelpMatchStatus());
        memberSessionEntityRepository.setMatchType(helpMatchRequest.getMemberId(), statusChangeEventDTO.getHelpMatchType());

        sendMatchEntityRepository.save(MatchEntityMapper.INSTANCE.toEntity(helpMatchRequest));

        String uuid = memberSessionEntityRepository.getServerUUID(helpMatchRequest.getMemberId());
        redisTemplate.convertAndSend(STATUS_CHANGE_EVENT_TOPIC_PREFIX +uuid, objectSerializer.serialize(statusChangeEventDTO));
    }

    private void matchInRange(Long memberId, Point point, double maxDistance,Set<Long> receiveMemberIdSet) {
        eventEmitter.emit(
                EventTopicPrefix.HELPER_SEARCH,
                HelperSearchEventDTO.builder().memberId(memberId).metric(maxDistance).build()
        );

        Long searchedMemberId;

        for(GeoResult<RedisGeoCommands.GeoLocation<String>> geoResult:memberPointRepository.search(point,maxDistance).getContent()){
            searchedMemberId = Long.parseLong(geoResult.getContent().getName());

            if(isMatchedAlready(searchedMemberId,receiveMemberIdSet)) continue;
            if(isMemberBusy(searchedMemberId)) continue;
            if(isMemberSelf(searchedMemberId,memberId)) continue;

            emitMatchPushEvent(memberId, searchedMemberId, receiveMemberIdSet);
        }
    }

    @Async
    void emitMatchPopEvent(Long memberId, Long receiveMemberId){
        sendMemberIdSetRepository.delete(receiveMemberId,memberId);

        if(memberSessionEntityRepository.isConnected(receiveMemberId)){
            String uuid = memberSessionEntityRepository.getServerUUID(receiveMemberId);

            MatchPopEventDTO eventDTO = MatchPopEventDTO
                    .builder()
                    .memberId(memberId)
                    .matchedMemberId(receiveMemberId)
                    .build();
            redisTemplate.convertAndSend(MATCH_POP_EVENT_TOPIC_PREFIX +uuid, objectSerializer.serialize(eventDTO));
        }
    }

    @Async
    void emitStatusChangeEvent(Long memberId){
        if(memberSessionEntityRepository.isConnected(memberId)){
            String uuid = memberSessionEntityRepository.getServerUUID(memberId);

            StatusChangeEventDTO eventDTO = StatusChangeEventDTO
                    .builder()
                    .memberId(memberId)
                    .build();

            redisTemplate.convertAndSend(STATUS_CHANGE_EVENT_TOPIC_PREFIX +uuid, objectSerializer.serialize(eventDTO));
        }
    }

    private void emitMatchPushEvent(Long memberId, Long searchedMemberId, Set<Long> matchedMemberIdSet){
        matchedMemberIdSet.add(searchedMemberId);
        sendMemberIdSetRepository.save(searchedMemberId,memberId);

        if(memberSessionEntityRepository.isConnected(searchedMemberId)){
            String uuid = memberSessionEntityRepository.getServerUUID(searchedMemberId);

            MatchPushEventDTO matchPushEventDTO = MatchPushEventDTO
                    .builder()
                    .memberId(memberId)
                    .matchedMemberId(searchedMemberId)
                    .build();

            redisTemplate.convertAndSend(MATCH_PUSH_EVENT_TOPIC_PREFIX +uuid, objectSerializer.serialize(matchPushEventDTO));
        }else{
            kafkaEventProducer.produce(KafkaEventTopic.HELP_MATCH_PUSH,searchedMemberId.toString());
        }
    }

    private boolean isMemberBusy(Long memberId){
        return !memberSessionEntityRepository.getMatchStatus(memberId).equals(HelpMatchStatus.DEFAULT);
    }

    private boolean isMemberSelf(Long memberId,Long searchedMemberId){
        return searchedMemberId.equals(memberId);
    }

    private boolean isMatchedAlready(Long searchedMemberId,Set<Long> matchedMemberIdSet){
        return matchedMemberIdSet.contains(searchedMemberId);
    }

}
