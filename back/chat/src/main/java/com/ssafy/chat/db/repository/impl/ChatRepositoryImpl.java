package com.ssafy.chat.db.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.chat.db.entity.ChatEntity;
import com.ssafy.chat.db.repository.ChatRepository;
import com.ssafy.chat.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatRepositoryImpl implements ChatRepository {

    private final ListOperations<String, String> listOperations;

    private final ObjectSerializer objectSerializer;

    @Override
    public void saveChat(ChatEntity chatEntity, int roomId) {
        String json = objectSerializer.serialize(chatEntity);
        listOperations.rightPush("chatRoomId:" + roomId, json);
    }

    @Override
    public ChatEntity getLastChat(int chatRoomId) {
        List<String> objectList = listOperations.getOperations().opsForList().range("chatRoomId:" + chatRoomId, -1, -1);
        return objectSerializer.deserialize(objectList.get(0), ChatEntity.class);
    }

}
