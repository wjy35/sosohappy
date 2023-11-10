package com.ssafy.help.match.event.producer;

import com.ssafy.help.match.event.dto.EventDTO;
import com.ssafy.help.match.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaEventProducer {
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectSerializer objectSerializer;

    public void produce(KafkaEventTopic topic, EventDTO eventDTO){
        kafkaTemplate.send(topic.toString(),objectSerializer.serialize(eventDTO));
    }

}
