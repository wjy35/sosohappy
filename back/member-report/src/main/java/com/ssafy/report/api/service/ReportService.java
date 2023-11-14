package com.ssafy.report.api.service;

import com.ssafy.report.db.entity.MemberReportEntity;

import java.util.List;

public interface ReportService {
    void reportMember(MemberReportEntity memberReportEntity);
    List<MemberReportEntity> getReportedMemberList();
}
