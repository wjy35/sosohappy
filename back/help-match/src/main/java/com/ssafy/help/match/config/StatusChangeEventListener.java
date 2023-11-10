package com.ssafy.help.match.config;

import com.ssafy.help.match.socket.dto.StatusChangeEventDTO;
import com.ssafy.help.match.socket.response.MatchStatusResponse;
import com.ssafy.help.match.socket.service.HelpMatchService;
import com.ssafy.help.match.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatusChangeEventListener implements MessageListener {
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final ObjectSerializer objectSerializer;
    private final HelpMatchService helpMatchService;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        StatusChangeEventDTO statusChangeEvent = objectSerializer.deserialize(message.toString(), StatusChangeEventDTO.class);
        MatchStatusResponse response = helpMatchService.getStatus(statusChangeEvent.getMemberId());
        simpMessageSendingOperations.convertAndSend("/topic/match/status/"+statusChangeEvent.getMemberId(), objectSerializer.serialize(response));
    }
}
