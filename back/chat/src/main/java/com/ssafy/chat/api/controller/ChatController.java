package com.ssafy.chat.api.controller;

import com.ssafy.chat.api.request.ChatPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping(value = "/test")
    public void message(ChatPayload chatPayload){
        System.out.println(chatPayload.toString());
        simpMessagingTemplate.convertAndSend("/topic/chat", chatPayload);
    }
}
