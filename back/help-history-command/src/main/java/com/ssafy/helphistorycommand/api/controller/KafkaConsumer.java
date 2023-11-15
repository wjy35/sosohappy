package com.ssafy.helphistorycommand.api.controller;


import com.ssafy.helphistorycommand.api.service.HelpHistoryService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
public class KafkaConsumer {

    private final HelpHistoryService helpHistoryService;
    @KafkaListener(topics = "help.history.create")
    public void consume(ConsumerRecord<String,String> message) {
        helpHistoryService.addHelpHistory(message);
        System.out.println("message = " + message.value());
    }
}
