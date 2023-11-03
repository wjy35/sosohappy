package com.ssafy.helphistorysync.api.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HelpHistoryRequest {
    private long historyId;
    private long toMemberId;
    private long fromMemberId;
    private long categoryId;
    private String content;
    private double x;
    private double y;
    private Timestamp createdAt;
}

