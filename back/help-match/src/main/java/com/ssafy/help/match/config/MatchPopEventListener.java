package com.ssafy.help.match.config;

import com.ssafy.help.match.socket.dto.MatchPopEventDTO;
import com.ssafy.help.match.socket.response.PopMatchListResponse;
import com.ssafy.help.match.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MatchPopEventListener implements MessageListener {
    private final ObjectSerializer objectSerializer;
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        MatchPopEventDTO eventDTO = objectSerializer.deserialize(message.toString(), MatchPopEventDTO.class);

        PopMatchListResponse response = PopMatchListResponse
                .builder()
                .memberIdList(List.of(eventDTO.getMemberId()))
                .build();

        simpMessageSendingOperations.convertAndSend(
                "/topic/match/list/"+eventDTO.getMatchedMemberId(),
                objectSerializer.serialize(response)
        );
    }
}
