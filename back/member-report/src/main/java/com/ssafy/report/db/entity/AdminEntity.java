package com.ssafy.report.db.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "admin")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminEntity {
    @Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer adminId;

    @Column(name = "member_id")
    Long memberId;
}
