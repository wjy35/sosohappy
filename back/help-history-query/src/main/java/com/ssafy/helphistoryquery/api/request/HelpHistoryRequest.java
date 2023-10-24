package com.ssafy.helphistoryquery.api.request;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Builder
public class HelpHistoryRequest {
    private long historyId;
    private long toMemberId;
    private long fromMemberId;
    private long categoryId;
    private String content;
    private BigDecimal x;
    private BigDecimal y;
    private Timestamp createdAt;
}



