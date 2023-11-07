package com.ssafy.help.match.socket.service.impl;

import com.ssafy.help.match.db.entity.HelpEntity;
import com.ssafy.help.match.db.entity.HelpMatchStatus;
import com.ssafy.help.match.db.entity.HelpMatchType;
import com.ssafy.help.match.db.entity.SendMatchEntity;
import com.ssafy.help.match.db.repository.*;
import com.ssafy.help.match.socket.dto.MatchEventDTO;
import com.ssafy.help.match.socket.dto.StatusChangeEventDTO;
import com.ssafy.help.match.socket.mapper.HelpEntityMapper;
import com.ssafy.help.match.socket.mapper.MatchEntityMapper;
import com.ssafy.help.match.socket.request.HelpAcceptRequest;
import com.ssafy.help.match.socket.request.HelpMatchRequest;
import com.ssafy.help.match.socket.response.MatchStatusResponse;
import com.ssafy.help.match.socket.response.ReceiveMatchItem;
import com.ssafy.help.match.socket.service.HelpMatchService;
import com.ssafy.help.match.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HelpMatchServiceImpl implements HelpMatchService {
    private final MemberSessionEntityRepository memberSessionEntityRepository;
    private final MemberPointRepository memberPointRepository;
    private final MemberMatchSetRepository memberMatchSetRepository;
    private final SendMatchEntityRepository sendMatchEntityRepository;
    private final RedisTemplate<String,String> redisTemplate;
    private final String MATCH_EVENT_PREFIX ="matchEvent:";
    private final String STATUS_CHANGE_EVENT_PREFIX="statusChangeEvent:";
    private final ObjectSerializer objectSerializer;
    private final double[] maxDistanceList = {500d,1000d,1500d};

    private final HelpEntityRepository helpEntityRepository;

    @Override
    public void accept(HelpAcceptRequest helpAcceptRequest) {
        Long helperMemberId = helpAcceptRequest.getHelperMemberId();
        Long disabledMemberId = helpAcceptRequest.getDisabledMemberId();

        SendMatchEntity sendMatchEntity = Optional.ofNullable(sendMatchEntityRepository.getAndDeleteByMemberId(disabledMemberId))
                .orElseThrow(); // ToDO UnAcceptable Exception
        if(isMatchCanceled(disabledMemberId)) throw new RuntimeException(); // UnAcceptable Exception

        try{
            memberSessionEntityRepository.setMatchStatus(helperMemberId, HelpMatchStatus.ON_MOVE);
            memberSessionEntityRepository.setMatchStatus(disabledMemberId, HelpMatchStatus.WAIT_COMPLETE);
            HelpEntity disabledHelpEntity = HelpEntityMapper.INSTANCE.matchToHelp(helperMemberId,sendMatchEntity);
            HelpEntity helperHelpEntity = HelpEntityMapper.INSTANCE.matchToHelp(disabledMemberId,sendMatchEntity);
            helpEntityRepository.save(disabledMemberId, disabledHelpEntity);
            helpEntityRepository.save(helperMemberId, helperHelpEntity);
        }catch (Exception e){
            memberSessionEntityRepository.setMatchStatus(helperMemberId, HelpMatchStatus.DEFAULT);
            memberSessionEntityRepository.setMatchStatus(disabledMemberId, HelpMatchStatus.DEFAULT);
            helpEntityRepository.getAndDeleteByMemberId(disabledMemberId);
            helpEntityRepository.getAndDeleteByMemberId(helperMemberId);
        }
    }

    private boolean isMatchCanceled(Long disabledMemberId){
        return !memberSessionEntityRepository.getMatchStatus(disabledMemberId).equals(HelpMatchStatus.ON_MATCH_PROGRESS);
    }

    @Override
    public MatchStatusResponse viewStatusByMemberId(Long memberId) {
        HelpMatchType helpMatchType = memberSessionEntityRepository.getMatchType(memberId);
        HelpMatchStatus helpMatchStatus = memberSessionEntityRepository.getMatchStatus(memberId);

        return MatchStatusResponse
                .builder()
                .helpMatchStatus(helpMatchStatus)
                .helpMatchType(helpMatchType)
                .build();
    }


    @Override
    public List<ReceiveMatchItem> list(Long memberId) {
        List<String> matchMemberIdList = new ArrayList<>(memberMatchSetRepository.getSet(memberId));
        List<ReceiveMatchItem> receiveMatchItemList = new ArrayList<>();

        MatchEntityMapper mapper = MatchEntityMapper.INSTANCE;

        for (String matchMemberId:matchMemberIdList){
            Optional.ofNullable(sendMatchEntityRepository.findByMemberId(Long.parseLong(matchMemberId)))
                    .ifPresent((sendMatchEntity) -> {
                        Double distance = memberPointRepository.getDistance(memberId,Long.parseLong(matchMemberId));
                        receiveMatchItemList.add(mapper.toItem(sendMatchEntity,distance));
                    });
        }

        return receiveMatchItemList;
    }


    @Override
    public void match(HelpMatchRequest helpMatchRequest) {
        saveAndChangeStatus(helpMatchRequest);

        Set<Long> matchedMemberIdSet = new HashSet<>();
        Point centerPoint = new Point(helpMatchRequest.getLongitude(),helpMatchRequest.getLatitude());

        for(double maxDistance:maxDistanceList){
            matchInRange(helpMatchRequest.getMemberId(), centerPoint, maxDistance, matchedMemberIdSet);
        }
    }

    private void saveAndChangeStatus(HelpMatchRequest helpMatchRequest) {
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
        redisTemplate.convertAndSend(STATUS_CHANGE_EVENT_PREFIX+uuid, objectSerializer.serialize(statusChangeEventDTO));
    }


    private void matchInRange(Long memberId, Point point, double maxDistance,Set<Long> matchedMemberIdSet) {
        Long searchedMemberId;

        for(GeoResult<RedisGeoCommands.GeoLocation<String>> geoResult:memberPointRepository.search(point,maxDistance).getContent()){
            searchedMemberId = Long.parseLong(geoResult.getContent().getName());

            if(isMatchedAlready(searchedMemberId,matchedMemberIdSet)) continue;
            if(isMemberBusy(searchedMemberId)) continue;
            if(isMemberSelf(searchedMemberId,memberId)) continue;

            emitMatchEvent(memberId, searchedMemberId, matchedMemberIdSet);
        }
    }

    private void emitMatchEvent(Long memberId, Long searchedMemberId, Set<Long> matchedMemberIdSet){
        matchedMemberIdSet.add(searchedMemberId);
        memberMatchSetRepository.save(searchedMemberId,memberId);

        if(memberSessionEntityRepository.isConnected(searchedMemberId)){
            String uuid = memberSessionEntityRepository.getServerUUID(searchedMemberId);

            MatchEventDTO matchEventDTO = MatchEventDTO
                    .builder()
                    .memberId(memberId)
                    .matchedMemberId(searchedMemberId)
                    .build();
            redisTemplate.convertAndSend(MATCH_EVENT_PREFIX +uuid, objectSerializer.serialize(matchEventDTO));
        }else{
            // ToDo Notification Event
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
