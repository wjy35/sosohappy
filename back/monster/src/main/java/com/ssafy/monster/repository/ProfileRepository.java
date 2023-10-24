package com.ssafy.monster.repository;

import com.ssafy.monster.domain.entity.MemberMonsterProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<MemberMonsterProfile, Long> {

    Optional<MemberMonsterProfile> findByMemberId(Long memberId);

    @Query(value = "SELECT required_clover FROM monster_info WHERE type_id = 1", nativeQuery = true)
    List<Integer> getMonsterCloverInfo();

    @Query(value = "SELECT monster_level FROM monster_info WHERE type_id = 1", nativeQuery = true)
    List<Integer> getMonsterLevelInfo();

}
