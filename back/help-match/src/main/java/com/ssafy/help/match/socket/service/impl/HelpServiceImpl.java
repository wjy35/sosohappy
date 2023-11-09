package com.ssafy.help.match.socket.service.impl;

import com.ssafy.help.match.db.entity.HelpEntity;
import com.ssafy.help.match.db.entity.HelpMatchStatus;
import com.ssafy.help.match.db.entity.HelpMatchType;
import com.ssafy.help.match.db.entity.SendMatchEntity;
import com.ssafy.help.match.db.repository.HelpEntityRepository;
import com.ssafy.help.match.db.repository.MemberSessionEntityRepository;
import com.ssafy.help.match.db.repository.SendMatchEntityRepository;
import com.ssafy.help.match.db.repository.SendMemberIdSetRepository;
import com.ssafy.help.match.socket.dto.MatchPopEventDTO;
import com.ssafy.help.match.socket.dto.StatusChangeEventDTO;
import com.ssafy.help.match.socket.mapper.HelpEntityMapper;
import com.ssafy.help.match.socket.request.HelpAcceptRequest;
import com.ssafy.help.match.socket.service.HelpService;
import com.ssafy.help.match.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HelpServiceImpl implements HelpService {
    private final MemberSessionEntityRepository memberSessionEntityRepository;
    private final SendMatchEntityRepository sendMatchEntityRepository;
    private final HelpEntityRepository helpEntityRepository;
    private final RedisTemplate<String,String> redisTemplate;
    private final ObjectSerializer objectSerializer;
    private final SendMemberIdSetRepository sendMemberIdSetRepository;

    @Value("${redis.topic.match-pop-event.prefix}")
    public String MATCH_POP_EVENT_TOPIC_PREFIX;

    @Value("${redis.topic.status-change-event.prefix}")
    public String STATUS_CHANGE_EVENT_TOPIC_PREFIX;

    @Override
    public void accept(HelpAcceptRequest helpAcceptRequest) {
        Long helperMemberId = helpAcceptRequest.getHelperMemberId();
        Long disabledMemberId = helpAcceptRequest.getDisabledMemberId();

        // ToDo 둘이 매칭 된 상태인지 체크
        SendMatchEntity sendMatchEntity = sendMatchEntityRepository.getAndDeleteByMemberId(disabledMemberId);
        isAcceptableDisabled(disabledMemberId);
        isAcceptableHelper(helperMemberId);

        try {
            memberSessionEntityRepository.setMatchStatus(helperMemberId, HelpMatchStatus.ON_MOVE);
            memberSessionEntityRepository.setMatchType(helperMemberId,HelpMatchType.SINGLE);
            memberSessionEntityRepository.setMatchStatus(disabledMemberId, HelpMatchStatus.WAIT_COMPLETE);

            HelpEntity disabledHelpEntity = HelpEntityMapper.INSTANCE.matchToHelp(helperMemberId, sendMatchEntity);
            HelpEntity helperHelpEntity = HelpEntityMapper.INSTANCE.matchToHelp(disabledMemberId, sendMatchEntity);

            helpEntityRepository.save(disabledMemberId, disabledHelpEntity);
            helpEntityRepository.save(helperMemberId, helperHelpEntity);
        }catch (Exception e){
            memberSessionEntityRepository.setMatchStatus(helperMemberId, HelpMatchStatus.ON_MATCH_PROGRESS);
            memberSessionEntityRepository.setMatchStatus(disabledMemberId, HelpMatchStatus.DEFAULT);

            helpEntityRepository.getAndDeleteByMemberId(disabledMemberId);
            helpEntityRepository.getAndDeleteByMemberId(helperMemberId);
        }

        emitStatusChangeEvent(helperMemberId);
        emitStatusChangeEvent(disabledMemberId);

        sendMatchEntityRepository.getAndDeleteReceiveMemberIdSet(disabledMemberId)
                .forEach((receiveMemberId)-> emitMatchPopEvent(disabledMemberId,receiveMemberId));
    }

    @Override
    public void arrival(Long memberId) {
        if(!memberSessionEntityRepository.isOnMove(memberId)) throw new RuntimeException();

        memberSessionEntityRepository.setMatchStatus(memberId, HelpMatchStatus.DEFAULT);
        memberSessionEntityRepository.setMatchType(memberId, HelpMatchType.NONE);
        helpEntityRepository.getAndDeleteByMemberId(memberId);

        emitStatusChangeEvent(memberId);
    }


    @Override
    public void complete(Long memberId) {
        if(!memberSessionEntityRepository.isWaitComplete(memberId)) throw new RuntimeException();

        memberSessionEntityRepository.setMatchStatus(memberId,HelpMatchStatus.DEFAULT);
        memberSessionEntityRepository.setMatchType(memberId,HelpMatchType.NONE);
        emitStatusChangeEvent(memberId);

        Optional.ofNullable(helpEntityRepository.getAndDeleteByMemberId(memberId))
                .ifPresent((helpEntity)->{
                    Long otherMemberId = helpEntity.getOtherMemberId();
                    if(memberSessionEntityRepository.isOnMove(otherMemberId)) {
                        memberSessionEntityRepository.setMatchStatus(otherMemberId,HelpMatchStatus.DEFAULT);
                        memberSessionEntityRepository.setMatchType(otherMemberId,HelpMatchType.NONE);
                        helpEntityRepository.getAndDeleteByMemberId(otherMemberId);
                        emitStatusChangeEvent(otherMemberId);
                    }
                });
        // ToDo help-history 기록
        // ToDo Clover 생성
    }

    @Override
    public void cancel(Long memberId) {
        if(memberSessionEntityRepository.isDefault(memberId)) return;
        memberSessionEntityRepository.setMatchStatus(memberId,HelpMatchStatus.DEFAULT);
        memberSessionEntityRepository.setMatchType(memberId,HelpMatchType.NONE);
        emitStatusChangeEvent(memberId);

        Optional.ofNullable(helpEntityRepository.getAndDeleteByMemberId(memberId))
                .ifPresent((helpEntity -> {
                    Long otherMemberId = helpEntity.getOtherMemberId();
                    if(memberSessionEntityRepository.isDefault(otherMemberId)) return;
                    memberSessionEntityRepository.setMatchStatus(otherMemberId,HelpMatchStatus.DEFAULT);
                    memberSessionEntityRepository.setMatchType(otherMemberId,HelpMatchType.NONE);
                    helpEntityRepository.getAndDeleteByMemberId(otherMemberId);
                    emitStatusChangeEvent(otherMemberId);
                }));
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

    private void isAcceptableHelper(Long memberId){
        if(!memberSessionEntityRepository.getMatchStatus(memberId).equals(HelpMatchStatus.DEFAULT)){
            throw new RuntimeException();
        }
    }

    private void isAcceptableDisabled(Long memberId){
        if(!memberSessionEntityRepository.getMatchStatus(memberId).equals(HelpMatchStatus.ON_MATCH_PROGRESS)){
            throw new RuntimeException();
        }
    }
}
