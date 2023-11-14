package com.ssafy.helphistorysync.api.mapper;

import com.ssafy.helphistorysync.api.dto.CategoryDto;
import com.ssafy.helphistorysync.api.dto.MemberDto;
import com.ssafy.helphistorysync.api.request.HelpHistoryRequest;
import com.ssafy.helphistorysync.db.entity.HelpCertificateEntity;
import com.ssafy.helphistorysync.db.entity.HelpHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface HelpHistoryMapper {
    HelpHistoryMapper INSTANCE = Mappers.getMapper(HelpHistoryMapper.class);

    @Mapping(target = "categoryId", source = "categoryDto.categoryId")
    @Mapping(target = "categoryName", source = "categoryDto.categoryName")
    @Mapping(target = "categoryImage", source = "categoryDto.categoryImage")
    @Mapping(target = "content", source = "helpHistoryRequest.content")
    @Mapping(target = "x", source = "helpHistoryRequest.x")
    @Mapping(target = "y", source = "helpHistoryRequest.y")
    @Mapping(target = "toMemberId", source = "helpHistoryRequest.toMemberId")
    @Mapping(target = "fromMemberId", source = "helpHistoryRequest.fromMemberId")
    @Mapping(target = "historyId", source = "helpHistoryRequest.historyId")
    @Mapping(target = "createdAt", source = "helpHistoryRequest.createdAt")
    HelpHistoryEntity requestToEntity(HelpHistoryRequest helpHistoryRequest, CategoryDto categoryDto);

}
