package com.ssafy.helphistorysync.api.mapper;

import com.ssafy.helphistorysync.api.request.HelpHistoryRequest;
import com.ssafy.helphistorysync.db.entity.HelpHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HelpHistoryMapper {
    HelpHistoryMapper INSTANCE = Mappers.getMapper(HelpHistoryMapper.class);

    HelpHistoryEntity requestToEntity(HelpHistoryRequest helpHistoryRequest);

}
