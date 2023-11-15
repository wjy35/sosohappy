package com.ssafy.report.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BannedMemberParam {
    Long memberId;
    String nickname;
    Timestamp endTimestamp;
}
