package com.ssafy.member.api.mapper;

import com.ssafy.member.api.request.MemberModifyRequest;
import com.ssafy.member.api.request.MemberSignUpRequest;
import com.ssafy.member.db.entity.MemberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberEntity toEntity(MemberSignUpRequest memberSignUpRequest);
    MemberEntity toEntity(Long memberId,MemberModifyRequest memberModifyRequest);
}
