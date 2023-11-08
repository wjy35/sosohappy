package com.ssafy.chat.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.chat.api.request.ChatPayload;
import com.ssafy.chat.api.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    private final ChatService chatService;

    @MessageMapping(value = "/test")
    public void message(ChatPayload chatPayload){

        try {
            chatService.saveChat(chatPayload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(chatPayload.toString());
        simpMessagingTemplate.convertAndSend("/topic/chat", chatPayload);
    }
}
