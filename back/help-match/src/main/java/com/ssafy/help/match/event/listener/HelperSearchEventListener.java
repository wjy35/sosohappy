package com.ssafy.help.match.event.listener;

import com.ssafy.help.match.event.dto.HelperSearchEventDTO;
import com.ssafy.help.match.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HelperSearchEventListener implements MessageListener {
    private final ObjectSerializer objectSerializer;
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    @Override
    public void onMessage(Message message, byte[] pattern) {
        HelperSearchEventDTO eventDTO = objectSerializer.deserialize(message.toString(), HelperSearchEventDTO.class);

        simpMessageSendingOperations.convertAndSend("/topic/match/progress/"+eventDTO.getMemberId(),message.toString());
    }
}
