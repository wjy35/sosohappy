package com.ssafy.chat.api.service.impl;

import com.ssafy.chat.api.request.ChatPublish;
import com.ssafy.chat.api.response.ChatResponse;
import com.ssafy.chat.api.service.ChatSendService;
import com.ssafy.chat.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatSendServiceImpl implements ChatSendService {

    private final ObjectSerializer objectSerializer;

    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @Override
    public void sendForDetail(ChatPublish chatPublish) {
        ChatResponse chatResponse = ChatResponse.builder()
                .memberId(chatPublish.getSendMemberId())
                .content(chatPublish.getContent())
                .timestamp(chatPublish.getTimestamp())
                .build();


        simpMessageSendingOperations.convertAndSend(
                chatPublish.getChatRoomDetailDestination(),
                objectSerializer.serialize(chatResponse)
        );

    }

    @Override
    public void sendForList(ChatPublish chatPublish) {
        ChatResponse chatResponse = ChatResponse.builder()
                .memberId(chatPublish.getSendMemberId())
                .content(chatPublish.getContent())
                .timestamp(chatPublish.getTimestamp())
                .build();

        simpMessageSendingOperations.convertAndSend(
                chatPublish.getChatRoomListDestination(),
                objectSerializer.serialize(chatResponse)
        );
    }
}
