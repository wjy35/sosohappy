package com.ssafy.helphistoryquery.db.repository;

import com.ssafy.helphistoryquery.db.entity.CertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateRepository extends JpaRepository<CertificateEntity, Long> {
    List<CertificateEntity> findAllByMemberId(long memberId);
}
