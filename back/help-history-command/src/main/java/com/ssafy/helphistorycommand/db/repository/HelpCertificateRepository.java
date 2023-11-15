package com.ssafy.helphistorycommand.db.repository;

import com.ssafy.helphistorycommand.db.entity.CertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelpCertificateRepository extends JpaRepository<CertificateEntity, Long> {
}
