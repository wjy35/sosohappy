package com.ssafy.helphistoryquery.api.mapper;

import com.ssafy.helphistoryquery.api.response.HelpHistoryResponse;
import com.ssafy.helphistoryquery.db.entity.CategoryEntity;
import com.ssafy.helphistoryquery.db.entity.HelpHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HelpHistoryMapper {
    HelpHistoryMapper INSTANCE = Mappers.getMapper(HelpHistoryMapper.class);
    @Mapping(target = "categoryId", source = "categoryEntity.categoryId")
    @Mapping(target = "categoryName", source = "categoryEntity.categoryName")
    @Mapping(target = "categoryImage", source = "categoryEntity.categoryImage")
    @Mapping(target = "historyId", source = "helpHistory.historyId")
    @Mapping(target = "content", source = "helpHistory.content")
    @Mapping(target = "createdAt", source = "helpHistory.createdAt")
    HelpHistoryResponse entityToResponse(CategoryEntity categoryEntity, HelpHistoryEntity helpHistory);

}
