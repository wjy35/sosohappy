package com.ssafy.chat.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.chat.api.request.ChatRoomCreateRequest;
import com.ssafy.chat.api.response.ChatRoomList;
import com.ssafy.chat.db.entity.ChatRoomEntity;

import java.util.List;

public interface ChatRoomService {

    Integer creatChatRoom(ChatRoomCreateRequest chatRoomCreateRequest);

    List<ChatRoomList> getChatRoomListParams(long memberId) throws JsonProcessingException;

    List<ChatRoomEntity> getChatRoomList(long memberId);

    void sendChatRoomList(List<ChatRoomList>chatRoomLists, long memberId);
}
