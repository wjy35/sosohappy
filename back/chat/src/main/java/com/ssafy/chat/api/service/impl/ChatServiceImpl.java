package com.ssafy.chat.api.service.impl;

import com.ssafy.chat.api.dto.ChatPublish;
import com.ssafy.chat.api.response.ChatResponse;
import com.ssafy.chat.api.service.ChatService;
import com.ssafy.chat.db.entity.ChatEntity;
import com.ssafy.chat.db.repository.ChatRepository;
import com.ssafy.chat.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;

    private final ObjectSerializer objectSerializer;

    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @Override
    public void sendForDetail(ChatPublish chatPublish) {
        ChatResponse chatResponse = ChatResponse.builder()
                .memberId(chatPublish.getSendMemberId())
                .content(chatPublish.getContent())
                .timestamp(chatPublish.getTimestamp())
                .build();

        List<ChatResponse> chatResponseList = new ArrayList<>();
        chatResponseList.add(chatResponse);

        simpMessageSendingOperations.convertAndSend(
                chatPublish.getChatRoomDetailDestination(),
                objectSerializer.serialize(chatResponseList)
        );

    }

    @Override
    public void sendForList(ChatPublish chatPublish) {
        ChatResponse chatResponse = ChatResponse.builder()
                .memberId(chatPublish.getSendMemberId())
                .content(chatPublish.getContent())
                .timestamp(chatPublish.getTimestamp())
                .build();
        List<ChatResponse> chatResponseList = new ArrayList<>();
        chatResponseList.add(chatResponse);
        simpMessageSendingOperations.convertAndSend(
                chatPublish.getChatRoomListDestination(),
                objectSerializer.serialize(chatResponseList)
        );
    }

    @Override
    public void sendForSelf(ChatPublish chatPublish) {
        ChatResponse chatResponse = ChatResponse.builder()
                .memberId(chatPublish.getSendMemberId())
                .content(chatPublish.getContent())
                .timestamp(chatPublish.getTimestamp())
                .build();

        List<ChatResponse> chatResponseList = new ArrayList<>();
        chatResponseList.add(chatResponse);

        simpMessageSendingOperations.convertAndSend(
                chatPublish.getChatRoomSelfDestination(),
                objectSerializer.serialize(chatResponseList)
        );
    }

    @Override
    public void saveChat(ChatEntity chatEntity, int roomId) {
        chatRepository.saveChat(chatEntity, roomId);
    }

    @Override
    public List<ChatResponse> getChatList(int chatRoomId) {
        List<ChatEntity> chatEntityList = chatRepository.getChatList(chatRoomId);

        return chatEntityList.stream()
                .map(chatEntity -> ChatResponse.builder()
                        .memberId(chatEntity.getSendMemberId())
                        .content(chatEntity.getContent())
                        .timestamp(chatEntity.getTimestamp())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void sendChatList(List<ChatResponse> chatResponseList, long memberId, int chatRoomId) {
        simpMessageSendingOperations.convertAndSend(
                "/topic/"+memberId+"/"+chatRoomId,
                objectSerializer.serialize(chatResponseList)
        );
    }
}
