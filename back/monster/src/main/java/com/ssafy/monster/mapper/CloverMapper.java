package com.ssafy.monster.mapper;

import com.ssafy.monster.domain.entity.MemberMonsterGrowth;
import com.ssafy.monster.domain.entity.MemberMonsterProfile;
import com.ssafy.monster.domain.res.CloverRes;
import com.ssafy.monster.domain.res.MonsterRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CloverMapper {
    CloverMapper INSTANCE = Mappers.getMapper(CloverMapper.class);

    CloverRes toCloverRes(MemberMonsterProfile profile);

}
