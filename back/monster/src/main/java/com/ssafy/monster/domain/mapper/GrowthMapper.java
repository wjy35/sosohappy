package com.ssafy.monster.domain.mapper;

import com.ssafy.monster.domain.entity.MemberMonsterGrowth;
import com.ssafy.monster.domain.entity.MemberMonsterProfile;
import com.ssafy.monster.domain.entity.MonsterType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GrowthMapper {

    GrowthMapper INSTANCE = Mappers.getMapper(GrowthMapper.class);
    MemberMonsterGrowth toGrowthEntity(MemberMonsterProfile memberMonsterProfile, MonsterType monsterType, int monsterClover);

}
