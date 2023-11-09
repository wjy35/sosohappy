package com.ssafy.help.match.event.listener;

import com.ssafy.help.match.event.dto.PointChangeEventDTO;
import com.ssafy.help.match.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PointChangeEventListener implements MessageListener {
    private final ObjectSerializer objectSerializer;
    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        PointChangeEventDTO pointChangeEvent = objectSerializer.deserialize(message.toString(), PointChangeEventDTO.class);

        simpMessageSendingOperations.convertAndSend("/topic/help/wait/"+pointChangeEvent.getReceiveMemberId(),message.toString());
    }
}
