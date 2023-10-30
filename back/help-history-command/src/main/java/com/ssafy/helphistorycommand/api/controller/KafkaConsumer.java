package com.ssafy.helphistorycommand.api.controller;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;


@Controller
public class KafkaConsumer {

    @KafkaListener(topics = "help-history-command-mysql.help_history.help_history")
    public void consume(String message) {
        System.out.println("message = " + message);
    }
}
