package com.ssafy.chat.db.repository;

import com.ssafy.chat.db.entity.ChatEntity;

import java.util.List;

public interface ChatRepository {
    void saveChat(ChatEntity chatEntity, int roomId);

    ChatEntity getLastChat(int chatRoomId);

    List<ChatEntity> getChatList(int chatRoomId);
}
