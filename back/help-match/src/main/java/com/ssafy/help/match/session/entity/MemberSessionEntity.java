package com.ssafy.help.match.session.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberSessionEntity {
    Long memberId;
    String sessionId;
    Boolean isConnected;
    HelpMatchType matchType;
    HelpMatchStatus matchStatus;
}
