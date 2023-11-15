package com.ssafy.chat.event.producer;

import com.ssafy.chat.event.dto.ChatSendEventDTO;
import com.ssafy.chat.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaEventProducer {
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectSerializer objectSerializer;

    public void produce(KafkaEventTopic topic, ChatSendEventDTO chatSendEventDTO){
        kafkaTemplate.send(topic.toString(),objectSerializer.serialize(chatSendEventDTO));
    }
}
