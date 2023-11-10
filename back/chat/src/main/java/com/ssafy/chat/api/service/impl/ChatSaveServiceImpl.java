package com.ssafy.chat.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.chat.api.service.ChatSaveService;
import com.ssafy.chat.db.entity.ChatEntity;
import com.ssafy.chat.db.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatSaveServiceImpl implements ChatSaveService {

    private final ChatRepository chatRepository;

    @Override
    public void saveChat(ChatEntity chatEntity, int roomId) {
        chatRepository.saveChat(chatEntity, roomId);
    }
}
