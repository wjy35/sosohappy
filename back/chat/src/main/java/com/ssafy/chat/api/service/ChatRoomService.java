package com.ssafy.chat.api.service;

import com.ssafy.chat.api.request.ChatRoomCreateRequest;

public interface ChatRoomService {

    Integer creatChatRoom(ChatRoomCreateRequest chatRoomCreateRequest);
}
