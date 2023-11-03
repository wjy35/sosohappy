package com.ssafy.help.match.socket.mapper;

import com.ssafy.help.match.db.entity.SendMatchEntity;
import com.ssafy.help.match.socket.request.HelpMatchRequest;
import com.ssafy.help.match.socket.response.ReceiveMatchItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReceiveMatchMapper {
    ReceiveMatchMapper INSTANCE = Mappers.getMapper(ReceiveMatchMapper.class);

    ReceiveMatchItem toItem(SendMatchEntity sendMatchEntity);
    SendMatchEntity toEntity(HelpMatchRequest request);
}
