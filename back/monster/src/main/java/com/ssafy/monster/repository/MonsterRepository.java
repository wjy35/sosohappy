package com.ssafy.monster.repository;

import com.ssafy.monster.domain.entity.MemberMonsterGrowth;
import com.ssafy.monster.domain.entity.MemberMonsterProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonsterRepository extends JpaRepository<MemberMonsterProfile, Long> {

    Optional<MemberMonsterProfile> findByMemberId(Long memberId);

    @Query(value = "SELECT IFNULL(sum(required_clover),'0') FROM monster_info WHERE monster_level < ?1 and type_id = 1",nativeQuery = true)
    Double getPrevRequiredClover(int level);
}
