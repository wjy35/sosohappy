package com.ssafy.chat.api.service.impl;

import com.ssafy.chat.api.mapper.ChatRoomMapper;
import com.ssafy.chat.api.request.ChatRoomCreateRequest;
import com.ssafy.chat.api.service.ChatRoomService;
import com.ssafy.chat.db.entity.ChatRoomEntity;
import com.ssafy.chat.db.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    private final ChatRoomMapper chatRoomMapper;
    @Override
    public Integer creatChatRoom(ChatRoomCreateRequest chatRoomCreateRequest) {
        Optional<ChatRoomEntity> chatRoomEntityOption = chatRoomRepository.findByChatRoomByUserIds(chatRoomCreateRequest.getSenderMemberId(), chatRoomCreateRequest.getReceiverMemberId());
        if(chatRoomEntityOption.isEmpty()){
            ChatRoomEntity chatRoomEntity = chatRoomMapper.createRequestToEntity(chatRoomCreateRequest);
            return chatRoomRepository.save(chatRoomEntity).getChatRoomId();
        }else {
            return chatRoomEntityOption.get().getChatRoomId();
        }
    }
}
