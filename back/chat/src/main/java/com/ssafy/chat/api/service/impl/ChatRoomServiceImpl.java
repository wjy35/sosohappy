package com.ssafy.chat.api.service.impl;

import com.ssafy.chat.api.mapper.ChatRoomMapper;
import com.ssafy.chat.api.request.ChatRoomCreateRequest;
import com.ssafy.chat.api.service.ChatRoomService;
import com.ssafy.chat.db.entity.ChatRoomEntity;
import com.ssafy.chat.db.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    private final ChatRoomMapper chatRoomMapper;
    @Override
    public void creatChatRoom(ChatRoomCreateRequest chatRoomCreateRequest) {
        ChatRoomEntity chatRoomEntity = chatRoomMapper.createRequestToEntity(chatRoomCreateRequest);

        System.out.println("chatRoomEntity = " + chatRoomEntity);
        chatRoomRepository.save(chatRoomEntity);
    }
}
