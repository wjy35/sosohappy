package com.ssafy.report.api.service;

import com.ssafy.report.api.response.ReportedMemberResponse;
import com.ssafy.report.db.entity.MemberReportEntity;
import java.util.List;

public interface ReportService {
    void reportMember(MemberReportEntity memberReportEntity);
    List<ReportedMemberResponse> getReportedMemberList();
}
