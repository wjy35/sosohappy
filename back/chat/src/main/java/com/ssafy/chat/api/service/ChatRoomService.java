package com.ssafy.chat.api.service;

import com.ssafy.chat.api.request.ChatRoomCreateRequest;
import com.ssafy.chat.api.response.ChatRoomListParam;
import com.ssafy.chat.db.entity.ChatRoomEntity;

import java.util.List;

public interface ChatRoomService {

    Integer creatChatRoom(ChatRoomCreateRequest chatRoomCreateRequest);

    List<ChatRoomListParam> getChatRoomListParams(long memberId);

    List<ChatRoomEntity> getChatRoomList(long memberId);
}
