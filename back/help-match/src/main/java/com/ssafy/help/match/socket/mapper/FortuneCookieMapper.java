package com.ssafy.help.match.socket.mapper;

import com.ssafy.help.match.db.entity.FortuneCookieEntity;
import com.ssafy.help.match.socket.response.FortuneCookieItem;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FortuneCookieMapper {
    FortuneCookieMapper INSTANCE = Mappers.getMapper(FortuneCookieMapper.class);

    @Named("TO_ITEM")
    FortuneCookieItem toItem(FortuneCookieEntity fortuneCookieEntity);


    @IterableMapping(qualifiedByName = "TO_ITEM")
    List<FortuneCookieItem> toItemList(List<FortuneCookieEntity> fortuneCookieEntityList);
}
