package com.ssafy.helphistorycommand.db.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "certificate")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CertificateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long certificateId;

    @Column(name = "member_id", nullable = false)
    long memberId;

    @Column(name = "certificate_num", nullable = false)
    String certificateNum;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    Timestamp createdAt;

    @Column(name = "exported_at")
    Timestamp exportedAt;
}
