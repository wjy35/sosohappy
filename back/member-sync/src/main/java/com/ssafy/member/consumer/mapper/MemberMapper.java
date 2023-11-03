package com.ssafy.member.consumer.mapper;

import com.ssafy.member.consumer.dto.MemberDTO;
import com.ssafy.member.db.entity.MemberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    MemberEntity toEntity(MemberDTO memberDTO);
}
