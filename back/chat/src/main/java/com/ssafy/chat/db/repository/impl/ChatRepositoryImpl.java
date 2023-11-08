package com.ssafy.chat.db.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.chat.db.entity.ChatEntity;
import com.ssafy.chat.db.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ChatRepositoryImpl implements ChatRepository {

    private final ListOperations<String, String> listOperations;

    private final ObjectMapper objectMapper;

    @Override
    public void saveChat(ChatEntity chatEntity, int roomId) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(objectMapper.convertValue(chatEntity, Map.class));
        listOperations.rightPush("chatRoomId:" + roomId, json);
    }
}
