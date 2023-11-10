package com.ssafy.help.match.config;

import com.ssafy.help.match.db.entity.SendMatchEntity;
import com.ssafy.help.match.db.repository.MemberPointRepository;
import com.ssafy.help.match.db.repository.SendMatchEntityRepository;
import com.ssafy.help.match.socket.dto.MatchPushEventDTO;
import com.ssafy.help.match.socket.mapper.MatchEntityMapper;
import com.ssafy.help.match.socket.response.PushMatchItem;
import com.ssafy.help.match.socket.response.PushMatchListResponse;
import com.ssafy.help.match.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MatchPushEventListener implements MessageListener {
    private final SendMatchEntityRepository sendMatchEntityRepository;
    private final MemberPointRepository memberPointRepository;
    private final ObjectSerializer objectSerializer;
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        MatchPushEventDTO matchPushEventDTO = objectSerializer.deserialize(message.toString(), MatchPushEventDTO.class);
        SendMatchEntity sendMatchEntity = sendMatchEntityRepository.findByMemberId(matchPushEventDTO.getMemberId());
        if(Optional.ofNullable(sendMatchEntity).isEmpty()) return;

        Double distance = memberPointRepository.getDistance(matchPushEventDTO.getMemberId(), matchPushEventDTO.getMatchedMemberId());

        List<PushMatchItem> receiveMatchList = List.of(MatchEntityMapper.INSTANCE.toItem(sendMatchEntity, distance));

        PushMatchListResponse response = PushMatchListResponse
                .builder()
                .receiveMatchList(receiveMatchList)
                .build();

        simpMessageSendingOperations.convertAndSend(
                "/topic/match/list/"+ matchPushEventDTO.getMatchedMemberId(),
                objectSerializer.serialize(response)
        );
    }
}
