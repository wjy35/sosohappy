package com.ssafy.helphistoryquery.db.entity;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Getter
@Builder
public class HelpHistoryEntity {
    private long historyId;
    private long toMemberId;
    private long fromMemberId;
    private long categoryId;
    private String content;
    private BigDecimal x;
    private BigDecimal y;
    private Timestamp createdAt;
}
