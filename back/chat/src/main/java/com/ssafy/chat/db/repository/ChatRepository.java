package com.ssafy.chat.db.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.chat.db.entity.ChatEntity;

public interface ChatRepository {
    void saveChat(ChatEntity chatEntity, int roomId) throws JsonProcessingException;

    ChatEntity getLastChat(int chatRoomId);
}
