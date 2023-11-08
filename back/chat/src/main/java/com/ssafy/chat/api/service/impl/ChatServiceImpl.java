package com.ssafy.chat.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.chat.api.mapper.ChatMapper;
import com.ssafy.chat.api.request.ChatPayload;
import com.ssafy.chat.api.service.ChatService;
import com.ssafy.chat.db.entity.ChatEntity;
import com.ssafy.chat.db.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    private final ChatMapper chatMapper;

    @Override
    public void saveChat(ChatPayload chatPayload) throws JsonProcessingException {
        ChatEntity chatEntity = chatMapper.createRequestToEntity(chatPayload);
        chatRepository.saveChat(chatEntity, chatPayload.getRoomId());
    }
}
