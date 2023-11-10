package com.ssafy.chat.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.chat.db.entity.ChatEntity;

public interface ChatSaveService {
    void saveChat(ChatEntity chatEntity, int roomId) throws JsonProcessingException;
}
