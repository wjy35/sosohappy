package com.ssafy.report.api.mapper;

import com.ssafy.report.api.response.BannedMemberParam;
import com.ssafy.report.db.entity.BannedMemberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BannedMemberMapper {
    BannedMemberMapper INSTANCE = Mappers.getMapper(BannedMemberMapper.class);

    BannedMemberParam toParam(BannedMemberEntity bannedMemberEntity,String nickname);
}
