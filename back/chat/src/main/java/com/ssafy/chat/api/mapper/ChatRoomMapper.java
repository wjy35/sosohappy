package com.ssafy.chat.api.mapper;

import com.ssafy.chat.api.request.ChatRoomCreateRequest;
import com.ssafy.chat.api.response.ChatRoomList;
import com.ssafy.chat.api.response.CurrentChat;
import com.ssafy.chat.db.entity.ChatEntity;
import com.ssafy.chat.db.entity.ChatRoomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatRoomMapper {
    ChatRoomMapper INSTANCE = Mappers.getMapper(ChatRoomMapper.class);

    ChatRoomEntity createRequestToEntity(ChatRoomCreateRequest chatRoomCreateRequest);

    CurrentChat entityToParam(ChatEntity chatEntity);

    ChatRoomList entityToParam(int chatRoomId, List<Long> memberList, CurrentChat currentChat);
}
