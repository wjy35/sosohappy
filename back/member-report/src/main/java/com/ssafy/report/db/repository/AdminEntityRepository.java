package com.ssafy.report.db.repository;

import com.ssafy.report.db.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminEntityRepository extends JpaRepository<AdminEntity,Integer> {
    boolean existsAdminEntityByMemberId(Long memberId);
}
