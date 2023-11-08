package com.ssafy.help.match.socket.mapper;

import com.ssafy.help.match.db.entity.SendMatchEntity;
import com.ssafy.help.match.socket.request.HelpMatchRequest;
import com.ssafy.help.match.socket.response.PushMatchItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchEntityMapper {
    MatchEntityMapper INSTANCE = Mappers.getMapper(MatchEntityMapper.class);

    PushMatchItem toItem(SendMatchEntity sendMatchEntity, Double distance);
    SendMatchEntity toEntity(HelpMatchRequest request);
}
