package com.ssafy.monster.domain.mapper;

import com.ssafy.monster.domain.entity.MemberMonsterGrowth;
import com.ssafy.monster.domain.entity.MemberMonsterProfile;
import com.ssafy.monster.domain.res.MonsterRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MonsterMapper {
    MonsterMapper INSTANCE = Mappers.getMapper(MonsterMapper.class);

    @Mapping(source="growth.memberMonsterId", target="memberMonsterId")
    @Mapping(source="profile.monsterInfo.monsterType.typeId", target="typeId")
    @Mapping(source="profile.monsterInfo.monsterType.typeName", target="typeName")
    @Mapping(source="profile.monsterInfo.monsterLevel", target="level")
    MonsterRes toRepresentativeMonsterRes(MemberMonsterProfile profile, MemberMonsterGrowth growth, Double currentPoint);

    @Mapping(source="growth.memberMonsterId", target="memberMonsterId")
    @Mapping(source="growth.monsterType.typeId", target="typeId")
    @Mapping(source="growth.monsterType.typeName", target="typeName")
    MonsterRes toLMonsterRes(MemberMonsterGrowth growth, Integer level, Double currentPoint);

}
