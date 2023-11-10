package com.ssafy.help.match.socket.mapper;

import com.ssafy.help.match.db.entity.FortuneCookieEntity;
import com.ssafy.help.match.socket.response.FortuneCookieItem;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FortuneCookieMapper {
    FortuneCookieMapper INSTANCE = Mappers.getMapper(FortuneCookieMapper.class);

    @IterableMapping
    List<FortuneCookieItem> toItem(List<FortuneCookieEntity> fortuneCookieEntityList);
}
