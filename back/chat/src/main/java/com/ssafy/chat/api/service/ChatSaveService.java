package com.ssafy.chat.api.service;

import com.ssafy.chat.db.entity.ChatEntity;

public interface ChatSaveService {
    void saveChat(ChatEntity chatEntity, int roomId);
}
