package com.ssafy.help.match.socket.mapper;

import com.ssafy.help.match.socket.dto.StatusChangeEventDTO;
import com.ssafy.help.match.socket.response.MatchStatusResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchStatusMapper {
    MatchStatusMapper INSTANCE = Mappers.getMapper(MatchStatusMapper.class);

    MatchStatusResponse eventToResponse(StatusChangeEventDTO statusChangeEventDTO);
}
