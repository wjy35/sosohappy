package com.ssafy.chat.api.mapper;

import com.ssafy.chat.api.request.ChatRoomCreateRequest;
import com.ssafy.chat.db.entity.ChatRoomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChatRoomMapper {
    ChatRoomMapper INSTANCE = Mappers.getMapper(ChatRoomMapper.class);

    ChatRoomEntity createRequestToEntity(ChatRoomCreateRequest chatRoomCreateRequest);

}
