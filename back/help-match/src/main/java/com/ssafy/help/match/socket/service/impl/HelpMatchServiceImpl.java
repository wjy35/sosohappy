package com.ssafy.help.match.socket.service.impl;

import com.ssafy.help.match.db.entity.HelpMatchStatus;
import com.ssafy.help.match.db.entity.HelpMatchType;
import com.ssafy.help.match.db.repository.SendMatchEntityRepository;
import com.ssafy.help.match.db.repository.MemberMatchSetRepository;
import com.ssafy.help.match.db.repository.MemberPointRepository;
import com.ssafy.help.match.db.repository.MemberSessionEntityRepository;
import com.ssafy.help.match.socket.dto.MatchEventDTO;
import com.ssafy.help.match.socket.dto.StatusChangeEventDTO;
import com.ssafy.help.match.socket.mapper.MatchEntityMapper;
import com.ssafy.help.match.socket.request.HelpMatchRequest;
import com.ssafy.help.match.socket.response.MatchStatusResponse;
import com.ssafy.help.match.socket.response.ReceiveMatchItem;
import com.ssafy.help.match.socket.service.HelpMatchService;
import com.ssafy.help.match.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void saveAndChangeStatus(HelpMatchRequest helpMatchRequest) {
        StatusChangeEventDTO statusChangeEventDTO = StatusChangeEventDTO
                .builder()
                .memberId(helpMatchRequest.getMemberId())
                .helpMatchStatus(HelpMatchStatus.ON_MATCH_PROGRESS)
                .helpMatchType(HelpMatchType.SINGLE)
                .build();

        String uuid = memberSessionEntityRepository.getServerUUID(helpMatchRequest.getMemberId());
        memberSessionEntityRepository.setMatchStatus(helpMatchRequest.getMemberId(),statusChangeEventDTO.getHelpMatchStatus());
        memberSessionEntityRepository.setMatchType(helpMatchRequest.getMemberId(), statusChangeEventDTO.getHelpMatchType());
        sendMatchEntityRepository.save(MatchEntityMapper.INSTANCE.toEntity(helpMatchRequest));
        redisTemplate.convertAndSend(STATUS_CHANGE_EVENT_PREFIX+uuid, objectSerializer.serialize(statusChangeEventDTO));
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
    public void match(Point point,double m,Long memberId) {
        memberPointRepository.search(point,m)
                .getContent()
                .stream()
                .map((geoResult)->Long.parseLong(geoResult.getContent().getName()))
                .forEach((searchedMemberId)-> emitMatchEvent(memberId,searchedMemberId));
    }

    private void emitMatchEvent(Long memberId, Long searchedMemberId){
        if(isNotMatchableMember(memberId,searchedMemberId)) return;

        sendMatchEntityRepository.add(memberId,searchedMemberId);
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

    private boolean isNotMatchableMember(Long memberId, Long searchedMemberId){
        return isBusy(searchedMemberId) || isAlreadyMatched(memberId,searchedMemberId) || isSelf(memberId,searchedMemberId);
    }

    private boolean isBusy(Long memberId){
        return !memberSessionEntityRepository.getMatchStatus(memberId).equals(HelpMatchStatus.DEFAULT);
    }

    private boolean isSelf(Long memberId,Long searchedMemberId){
        return searchedMemberId.equals(memberId);
    }

    private boolean isAlreadyMatched(Long memberId,Long searchedMemberId){
        return sendMatchEntityRepository.isMember(memberId,searchedMemberId);
    }

}
