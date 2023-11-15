package com.ssafy.report.db.repository;

import com.ssafy.report.db.entity.BannedMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannedMemberEntityRepository extends JpaRepository<BannedMemberEntity,Long> {
}
