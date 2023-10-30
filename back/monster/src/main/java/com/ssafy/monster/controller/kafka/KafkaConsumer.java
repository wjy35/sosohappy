package com.ssafy.monster.controller.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

@Controller
public class KafkaConsumer {
    @KafkaListener(topics = "member-command-mysql.member.member")
    public void consume(String message) {
        System.out.println("message = " + message);
    }
}
