package com.ssafy.help.match.socket.mapper;

import com.ssafy.help.match.db.entity.HelpEntity;
import com.ssafy.help.match.db.entity.SendMatchEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HelpEntityMapper {
    HelpEntityMapper INSTANCE = Mappers.getMapper(HelpEntityMapper.class);

    HelpEntity matchToHelp(Long otherMemberId, SendMatchEntity sendMatchEntity);
}
