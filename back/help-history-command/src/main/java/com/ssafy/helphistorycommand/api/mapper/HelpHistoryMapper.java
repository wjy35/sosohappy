package com.ssafy.helphistorycommand.api.mapper;

import com.ssafy.helphistorycommand.api.request.HelpHistoryRequest;
import com.ssafy.helphistorycommand.db.entity.HelpHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HelpHistoryMapper {
    HelpHistoryMapper INSTANCE = Mappers.getMapper(HelpHistoryMapper.class);

    HelpHistoryEntity requestToEntity(HelpHistoryRequest helpHistoryRequest);

}
