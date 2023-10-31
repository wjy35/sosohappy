package com.ssafy.member.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.OptionalInt;

@Component
@RequiredArgsConstructor
public class KafkaEventMapper {
    private final ObjectMapper objectMapper;

    public <T>T toEvent(ConsumerRecord<?,?> consumerRecord,Class<T> eventClass){
        String eventMessage = (String)consumerRecord.value();

        try {
            return objectMapper.readValue(eventMessage,eventClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
