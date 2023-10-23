package com.ssafy.monster.repository;

import com.ssafy.monster.domain.entity.MemberMonsterGrowth;
import com.ssafy.monster.domain.entity.MemberMonsterProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GrowthRepository extends JpaRepository<MemberMonsterGrowth, Long> {

    Optional<MemberMonsterGrowth> findByMemberMonsterProfile_MemberIdAndMonsterType_TypeId(Long memberId, Byte typeId);

    List<MemberMonsterGrowth> findAllByMemberMonsterProfile_MemberId(Long memberId);


}
