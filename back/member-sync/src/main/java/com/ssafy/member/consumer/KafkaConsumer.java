package com.ssafy.member.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.member.consumer.event.MemberEvent;
import com.ssafy.member.util.KafkaEventMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final KafkaEventMapper kafkaEventMapper;

    @KafkaListener(topics = "member-command-mysql.member.member")
    public void consume(ConsumerRecord<?,?> consumerRecord) throws JsonProcessingException {
        if(isEmptyEvent(consumerRecord)) return;

        MemberEvent memberEvent = kafkaEventMapper.toEvent(consumerRecord, MemberEvent.class);
        System.out.println("memberEvent = " + memberEvent);
    }

    private boolean isEmptyEvent(ConsumerRecord<?,?> consumerRecord){
        return Optional.ofNullable(consumerRecord.value()).isEmpty();
    }

}