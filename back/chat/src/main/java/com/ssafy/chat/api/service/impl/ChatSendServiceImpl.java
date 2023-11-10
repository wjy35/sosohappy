package com.ssafy.chat.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.chat.api.request.ChatPublish;
import com.ssafy.chat.api.response.ChatResponse;
import com.ssafy.chat.api.service.ChatSendService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatSendServiceImpl implements ChatSendService {

    private final ObjectMapper objectMapper;

    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @Override
    public void sendForDetail(ChatPublish chatPublish) {
        ChatResponse chatResponse = ChatResponse.builder()
                .memberId(chatPublish.getSendMemberId())
                .content(chatPublish.getContent())
                .timestamp(chatPublish.getTimestamp())
                .build();

        try {
            simpMessageSendingOperations.convertAndSend(
                    chatPublish.getChatRoomDetailDestination(),
            objectMapper.writeValueAsString(chatResponse)
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendForList(ChatPublish chatPublish) {
        ChatResponse chatResponse = ChatResponse.builder()
                .memberId(chatPublish.getSendMemberId())
                .content(chatPublish.getContent())
                .timestamp(chatPublish.getTimestamp())
                .build();
        try {
            simpMessageSendingOperations.convertAndSend(
                    chatPublish.getChatRoomListDestination(),
                    objectMapper.writeValueAsString(chatResponse)
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
