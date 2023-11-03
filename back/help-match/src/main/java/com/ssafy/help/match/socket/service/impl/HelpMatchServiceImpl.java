package com.ssafy.help.match.socket.service.impl;

import com.ssafy.help.match.db.entity.HelpMatchStatus;
import com.ssafy.help.match.db.entity.HelpMatchType;
import com.ssafy.help.match.db.repository.SendMatchEntityRepository;
import com.ssafy.help.match.db.repository.MemberMatchSetRepository;
import com.ssafy.help.match.db.repository.MemberPointRepository;
import com.ssafy.help.match.db.repository.MemberSessionEntityRepository;
import com.ssafy.help.match.socket.dto.MatchEventDTO;
import com.ssafy.help.match.socket.mapper.ReceiveMatchMapper;
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
    private final String PREFIX="matchEvent:";
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
    public MatchStatusResponse saveAndGetMatchStatus(HelpMatchRequest helpMatchRequest) {
        MatchStatusResponse matchStatusResponse = MatchStatusResponse
                .builder()
                .helpMatchStatus(HelpMatchStatus.ON_MATCH_PROGRESS)
                .helpMatchType(HelpMatchType.SINGLE)
                .build();

        memberSessionEntityRepository.setMatchStatus(helpMatchRequest.getMemberId(),matchStatusResponse.getHelpMatchStatus());
        memberSessionEntityRepository.setMatchType(helpMatchRequest.getMemberId(), matchStatusResponse.getHelpMatchType());
        sendMatchEntityRepository.save(ReceiveMatchMapper.INSTANCE.toEntity(helpMatchRequest));

        return matchStatusResponse;
    }

    @Override
    public List<ReceiveMatchItem> list(Long memberId) {
        List<String> matchMemberIdList = new ArrayList<>(memberMatchSetRepository.getSet(memberId));
        List<ReceiveMatchItem> receiveMatchItemList = new ArrayList<>();

        ReceiveMatchMapper mapper = ReceiveMatchMapper.INSTANCE;

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
                .filter((geoResult)-> isGeoResultAvailable(geoResult,memberId))
                .forEach((geoResult)-> emitMatchEvent(memberId,Long.parseLong(geoResult.getContent().getName())));
    }

    private void emitMatchEvent(Long memberId, Long matchedMemberId){
        memberMatchSetRepository.save(matchedMemberId,memberId);

        if(memberSessionEntityRepository.isConnected(matchedMemberId)){
            String uuid = memberSessionEntityRepository.getServerUUID(matchedMemberId);

            MatchEventDTO matchEventDTO = MatchEventDTO
                    .builder()
                    .memberId(memberId)
                    .matchedMemberId(matchedMemberId)
                    .build();
            redisTemplate.convertAndSend(PREFIX+uuid, objectSerializer.serialize(matchEventDTO));

        }else{
            // ToDo Notification Event
        }
    }

    private boolean isGeoResultAvailable(GeoResult<RedisGeoCommands.GeoLocation<String>> geoResult, Long memberId){
        Long searchedMemberId = Long.parseLong(geoResult.getContent().getName());
        return memberSessionEntityRepository
                .getMatchStatus(searchedMemberId)
                .equals(HelpMatchStatus.DEFAULT);
        // ToDo 자기 자신 제외하기
//                && !searchedMemberId.equals(memberId);
    }

}
