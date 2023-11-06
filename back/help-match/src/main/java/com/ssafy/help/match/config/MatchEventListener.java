package com.ssafy.help.match.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.help.match.db.entity.SendMatchEntity;
import com.ssafy.help.match.db.repository.MemberPointRepository;
import com.ssafy.help.match.db.repository.SendMatchEntityRepository;
import com.ssafy.help.match.socket.dto.MatchEventDTO;
import com.ssafy.help.match.socket.mapper.ReceiveMatchMapper;
import com.ssafy.help.match.socket.response.ReceiveMatchItem;
import com.ssafy.help.match.socket.response.ReceiveMatchListResponse;
import com.ssafy.help.match.socket.response.ReceiveMatchType;
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
public class MatchEventListener implements MessageListener {
    private final SendMatchEntityRepository sendMatchEntityRepository;
    private final MemberPointRepository memberPointRepository;
    private final ObjectSerializer objectSerializer;
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        MatchEventDTO matchEventDTO = objectSerializer.deserialize(message.toString(), MatchEventDTO.class);
        SendMatchEntity sendMatchEntity = sendMatchEntityRepository.findByMemberId(matchEventDTO.getMemberId());
        if(Optional.ofNullable(sendMatchEntity).isEmpty()) return;

        Double distance = memberPointRepository.getDistance(matchEventDTO.getMemberId(), matchEventDTO.getMatchedMemberId());

        List<ReceiveMatchItem> receiveMatchList = List.of(ReceiveMatchMapper.INSTANCE.toItem(sendMatchEntity, distance));

        ReceiveMatchListResponse response = ReceiveMatchListResponse
                .builder()
                .receiveMatchType(ReceiveMatchType.PUSH)
                .receiveMatchList(receiveMatchList)
                .build();

        simpMessageSendingOperations.convertAndSend(
                "/topic/match/list/"+matchEventDTO.getMatchedMemberId(),
                objectSerializer.serialize(response)
        );
    }
}
