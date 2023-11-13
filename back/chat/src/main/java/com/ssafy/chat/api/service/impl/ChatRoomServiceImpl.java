package com.ssafy.chat.api.service.impl;

import com.ssafy.chat.api.mapper.ChatRoomMapper;
import com.ssafy.chat.api.request.ChatRoomCreateRequest;
import com.ssafy.chat.api.response.ChatRoomList;
import com.ssafy.chat.api.response.CurrentChat;
import com.ssafy.chat.api.service.ChatRoomService;
import com.ssafy.chat.db.entity.ChatEntity;
import com.ssafy.chat.db.entity.ChatRoomEntity;
import com.ssafy.chat.db.repository.ChatRepository;
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

    private final ChatRepository chatRepository;

    private final ChatRoomMapper chatRoomMapper;
    @Override
    public Integer creatChatRoom(ChatRoomCreateRequest chatRoomCreateRequest) {
        Optional<ChatRoomEntity> chatRoomEntityOption = chatRoomRepository.findByChatRoomByUserIds(chatRoomCreateRequest.getSenderMemberId(), chatRoomCreateRequest.getReceiverMemberId());
        if(chatRoomEntityOption.isEmpty()){
            ChatRoomEntity chatRoomEntity = chatRoomMapper.createRequestToEntity(chatRoomCreateRequest);
            int chatRoomId = chatRoomRepository.save(chatRoomEntity).getChatRoomId();
            ChatEntity chatEntity = ChatEntity.builder()
                    .type(0)
                    .sendMemberId(0L)
                    .receiveMemberId(0L)
                    .content("채팅이 시작되었습니다.")
                    .build();
            chatRepository.saveChat(chatEntity, chatRoomId);
            return chatRoomId;
        }else {
            return chatRoomEntityOption.get().getChatRoomId();
        }
    }

    @Override
    public List<ChatRoomList> getChatRoomListParams(long memberId){

        List<ChatRoomList> chatRoomLists = new ArrayList<>();

        List<ChatRoomEntity> chatRoomList = getChatRoomList(memberId);

        for (ChatRoomEntity chatRoomEntity : chatRoomList) {
            ChatEntity chatEntity = chatRepository.getLastChat(chatRoomEntity.getChatRoomId());

            int chatRoomId = chatRoomEntity.getChatRoomId();

            List<Long> memberList = getMemberList(chatRoomEntity);

            CurrentChat currentChat = chatRoomMapper.entityToParam(chatEntity);
            currentChat.setMemberId(chatEntity.getSendMemberId());

            chatRoomLists.add(chatRoomMapper.entityToParam(chatRoomId, memberList, currentChat));
        }

        return chatRoomLists;
    }

    public List<Long> getMemberList(ChatRoomEntity chatRoomEntity){

        List<Long> memberList = new ArrayList<>();

        memberList.add(chatRoomEntity.getSenderMemberId());
        memberList.add(chatRoomEntity.getReceiverMemberId());

        return memberList;
    }

    @Override
    public List<ChatRoomEntity> getChatRoomList(long memberId) {
        return chatRoomRepository.findByReceiverMemberIdOrSenderMemberId(memberId, memberId);
    }


}
