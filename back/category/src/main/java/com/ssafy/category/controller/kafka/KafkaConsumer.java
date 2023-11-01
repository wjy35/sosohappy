package com.ssafy.category.controller.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class KafkaConsumer {

    @KafkaListener(topics = "help-history-command-mysql.help_history.help_history")
    public void consume(ConsumerRecord<String,String> record) {
        if(isEmptyEvent(record)) return;
        String message = record.value();
        System.out.println("message = " + message);
    }

    private boolean isEmptyEvent(ConsumerRecord<?,?> consumerRecord){
        return Optional.ofNullable(consumerRecord.value()).isEmpty();
    }
}
