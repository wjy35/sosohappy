package com.ssafy.report.db.entity;


import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "member_report")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberReportEntity {
    @Id
    @Column(name = "report_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long reportId;

    @Column(name = "reporting_member_id")
    Long reportingMemberId;

    @Column(name = "reported_member_id")
    Long reportedMemberId;
}
