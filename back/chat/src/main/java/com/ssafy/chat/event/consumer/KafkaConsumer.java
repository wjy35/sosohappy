package com.ssafy.chat.event.consumer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    @KafkaListener(topics = "chat.send")
    public void consume(ConsumerRecord<?,?> consumerRecord){
        if(isEmptyEvent(consumerRecord)) return;
        System.out.println("알림 발송 확인: "+consumerRecord.value());
    }

    private boolean isEmptyEvent(ConsumerRecord<?,?> consumerRecord){
        return Optional.ofNullable(consumerRecord.value()).isEmpty();
    }

}
