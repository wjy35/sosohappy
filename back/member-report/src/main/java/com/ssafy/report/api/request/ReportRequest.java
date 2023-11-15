package com.ssafy.report.api.request;

import lombok.Data;

@Data
public class ReportRequest {
    Long reportingMemberId;
    Long reportedMemberId;
}
