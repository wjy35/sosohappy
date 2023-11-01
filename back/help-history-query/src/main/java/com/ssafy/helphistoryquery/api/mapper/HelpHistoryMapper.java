package com.ssafy.helphistoryquery.api.mapper;

import com.ssafy.helphistoryquery.api.response.HelpHistoryResponse;
import com.ssafy.helphistoryquery.db.entity.HelpHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HelpHistoryMapper {
    HelpHistoryMapper INSTANCE = Mappers.getMapper(HelpHistoryMapper.class);
    @Mapping(target = "categoryId", source = "helpHistory.categoryId")
    @Mapping(target = "categoryName", source = "helpHistory.categoryName")
    @Mapping(target = "categoryImage", source = "helpHistory.categoryImage")
    @Mapping(target = "historyId", source = "helpHistory.historyId")
    @Mapping(target = "content", source = "helpHistory.content")
    @Mapping(target = "createdAt", source = "helpHistory.createdAt")
    HelpHistoryResponse entityToResponse(HelpHistoryEntity helpHistory);

}
