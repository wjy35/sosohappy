package com.ssafy.observer.db.entity;

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
    String serverUUID;
    Boolean isConnected;
    HelpMatchType matchType;
    HelpMatchStatus matchStatus;
}
