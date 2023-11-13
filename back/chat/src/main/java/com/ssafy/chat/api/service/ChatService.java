package com.ssafy.chat.api.service;

import com.ssafy.chat.api.dto.ChatPublish;
import com.ssafy.chat.api.response.ChatResponse;
import com.ssafy.chat.db.entity.ChatEntity;

import java.util.List;

public interface ChatService {
    void sendForDetail(ChatPublish chatPublish);
    void sendForList(ChatPublish chatPublish);
    void saveChat(ChatEntity chatEntity, int roomId);
    List<ChatResponse> getChatList(int chatRoomId);
}
