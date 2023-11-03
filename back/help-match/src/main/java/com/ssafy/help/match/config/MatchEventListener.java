package com.ssafy.help.match.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.help.match.socket.dto.MatchEventDTO;
import com.ssafy.help.match.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class MatchEventListener implements MessageListener {
    private final ObjectMapper objectMapper;
    private final ObjectSerializer objectSerializer;
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            MatchEventDTO matchEventDTO = objectMapper.readValue(message.getBody(), MatchEventDTO.class);
            simpMessageSendingOperations.convertAndSend("/topic/match/list/"+matchEventDTO.getMatchedMemberId(),message.getBody());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
