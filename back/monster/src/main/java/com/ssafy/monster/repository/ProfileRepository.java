package com.ssafy.monster.repository;

import com.ssafy.monster.domain.entity.MemberMonsterProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<MemberMonsterProfile, Long> {

    Optional<MemberMonsterProfile> findByMemberId(Long memberId);

    boolean existsByMemberId(Long memberId);
    void deleteByMemberId(Long memberId);

}
