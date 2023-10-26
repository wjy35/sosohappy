package com.ssafy.member.db.repository;

import com.ssafy.member.db.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberEntityRepository extends JpaRepository<MemberEntity,Long> {
}
