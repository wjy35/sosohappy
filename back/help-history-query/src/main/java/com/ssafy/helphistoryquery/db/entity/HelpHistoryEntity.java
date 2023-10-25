package com.ssafy.helphistoryquery.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;


@Getter
@ToString
@RequiredArgsConstructor
public class HelpHistoryEntity {
    private long historyId;
    private long toMemberId;
    private long fromMemberId;
    private long categoryId;
    private String content;
    private double x;
    private double y;
    private Timestamp createdAt;

    @Builder
    public HelpHistoryEntity(long historyId, long toMemberId, long fromMemberId, long categoryId, String content, double x, double y, Timestamp createdAt) {
        this.historyId = historyId;
        this.toMemberId = toMemberId;
        this.fromMemberId = fromMemberId;
        this.categoryId = categoryId;
        this.content = content;
        this.x = x;
        this.y = y;
        this.createdAt = createdAt;
    }
}

