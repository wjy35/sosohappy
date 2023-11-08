package com.ssafy.chat.api.mapper;

import com.ssafy.chat.api.request.ChatPayload;
import com.ssafy.chat.api.request.ChatRoomCreateRequest;
import com.ssafy.chat.db.entity.ChatEntity;
import com.ssafy.chat.db.entity.ChatRoomEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

    ChatEntity createRequestToEntity(ChatPayload chatPayload);
}
