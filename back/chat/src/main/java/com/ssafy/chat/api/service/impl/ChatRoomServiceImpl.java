package com.ssafy.chat.api.service.impl;

import com.ssafy.chat.api.mapper.ChatRoomMapper;
import com.ssafy.chat.api.request.ChatRoomCreateRequest;
import com.ssafy.chat.api.response.ChatParam;
import com.ssafy.chat.api.response.ChatRoomListParam;
import com.ssafy.chat.api.service.ChatRoomService;
import com.ssafy.chat.db.entity.ChatRoomEntity;
import com.ssafy.chat.db.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<ChatRoomListParam> getChatRoomListParams(long memberId) {
        List<ChatRoomListParam> chatRoomListParams = new ArrayList<>();

        List<ChatRoomEntity> chatRoomList = getChatRoomList(memberId);

        for(int i=0; i<chatRoomList.size(); i++){

            ChatRoomEntity chatRoomEntity = chatRoomList.get(i);

            long chatPartnerMemberId = memberId == chatRoomEntity.getReceiverMemberId()?chatRoomEntity.getSenderMemberId():chatRoomEntity.getReceiverMemberId();

            // ChatParam redis에서 memberId로 마지막 채팅 가져와
//            ChatParam chatParam =

        }

        return null;
    }

    @Override
    public List<ChatRoomEntity> getChatRoomList(long memberId) {
        return chatRoomRepository.findByReceiverMemberIdOrSenderMemberId(memberId, memberId);
    }


}
