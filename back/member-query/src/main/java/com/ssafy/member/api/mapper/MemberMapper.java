package com.ssafy.member.api.mapper;

import com.ssafy.member.api.response.MemberInformationResponse;
import com.ssafy.member.api.response.MemberPublicInformationResponse;
import com.ssafy.member.db.entity.MemberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberInformationResponse entityToResponse(MemberEntity memberEntity);
    MemberPublicInformationResponse toPublicResponse(MemberEntity memberEntity);
}
