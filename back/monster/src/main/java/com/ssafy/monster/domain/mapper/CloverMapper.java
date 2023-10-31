package com.ssafy.monster.domain.mapper;

import com.ssafy.monster.domain.entity.MemberMonsterProfile;
import com.ssafy.monster.domain.res.CloverRes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CloverMapper {
    CloverMapper INSTANCE = Mappers.getMapper(CloverMapper.class);

    CloverRes toCloverRes(MemberMonsterProfile profile);

}
