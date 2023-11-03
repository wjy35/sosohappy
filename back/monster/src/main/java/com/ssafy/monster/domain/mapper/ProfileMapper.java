package com.ssafy.monster.domain.mapper;

import com.ssafy.monster.domain.entity.MemberMonsterProfile;
import com.ssafy.monster.domain.entity.MonsterInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfileMapper {
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);
    MemberMonsterProfile toProfileEntity(Long memberId, int memberClover, Long memberAccruedClover, MonsterInfo monsterInfo);
}
