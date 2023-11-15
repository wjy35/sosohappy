package com.ssafy.report.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportedMemberResponse {
    String reportingNickname;
    String reportedNickname;
}
