package com.ssafy.chat.api.mapper;

import com.ssafy.chat.api.request.ChatRequest;
import com.ssafy.chat.api.dto.ChatPublish;
import com.ssafy.chat.db.entity.ChatEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

    ChatEntity createRequestToEntity(ChatRequest chatRequest);

    ChatPublish createChatPublish(ChatRequest chatRequest);
}
