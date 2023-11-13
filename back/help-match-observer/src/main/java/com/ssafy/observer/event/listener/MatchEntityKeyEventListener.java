package com.ssafy.observer.event.listener;

import com.ssafy.observer.db.entity.HelpMatchStatus;
import com.ssafy.observer.db.entity.HelpMatchType;
import com.ssafy.observer.db.repository.MemberSessionEntityRepository;
import com.ssafy.observer.db.repository.SendMatchEntityRepository;
import com.ssafy.observer.db.repository.SendMemberIdSetRepository;
import com.ssafy.observer.event.dto.MatchPopEventDTO;
import com.ssafy.observer.event.dto.StatusChangeEventDTO;
import com.ssafy.observer.event.emitter.RedisEventEmitter;
import com.ssafy.observer.event.emitter.RedisEventTopicPrefix;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.util.StringTokenizer;

@Component
@RequiredArgsConstructor
public class MatchEntityKeyEventListener implements MessageListener {
    private final String MATCH_ENTITY_PREFIX="matchEntity";
    private final MemberSessionEntityRepository memberSessionEntityRepository;
    private final SendMatchEntityRepository sendMatchEntityRepository;
    private final SendMemberIdSetRepository sendMemberIdSetRepository;
    private final RedisEventEmitter redisEventEmitter;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        StringTokenizer st = new StringTokenizer(message.toString(),":");

        String prefix = st.nextToken();
        if(isOtherEvent(prefix)) return;

        Long memberId = Long.parseLong(st.nextToken());

        memberSessionEntityRepository.setMatchStatus(memberId, HelpMatchStatus.DEFAULT);
        memberSessionEntityRepository.setMatchType(memberId, HelpMatchType.NONE);

        emitStatusChangeEvent(memberId);
        sendMatchEntityRepository.getAndDeleteReceiveMemberIdSet(memberId)
                .forEach((receiveMemberId)-> emitMatchPopEvent(memberId,receiveMemberId));

    }

    private boolean isOtherEvent(String key){
        return !key.equals(MATCH_ENTITY_PREFIX);
    }

    @Async
    void emitStatusChangeEvent(Long memberId){
        if(!memberSessionEntityRepository.isConnected(memberId))return;

        String uuid = memberSessionEntityRepository.getServerUUID(memberId);

        StatusChangeEventDTO eventDTO = StatusChangeEventDTO
                .builder()
                .memberId(memberId)
                .build();

        redisEventEmitter.emit(RedisEventTopicPrefix.STATUS_CHANGE,uuid,eventDTO);
    }

    @Async
    void emitMatchPopEvent(Long memberId, Long receiveMemberId){
        sendMemberIdSetRepository.delete(receiveMemberId,memberId);

        if(!memberSessionEntityRepository.isConnected(receiveMemberId)) return;

        String uuid = memberSessionEntityRepository.getServerUUID(receiveMemberId);

        MatchPopEventDTO eventDTO = MatchPopEventDTO
                .builder()
                .memberId(memberId)
                .matchedMemberId(receiveMemberId)
                .build();

        redisEventEmitter.emit(RedisEventTopicPrefix.MATCH_POP,uuid,eventDTO);
    }

}
