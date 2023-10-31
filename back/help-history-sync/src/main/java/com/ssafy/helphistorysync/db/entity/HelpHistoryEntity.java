package com.ssafy.helphistorysync.db.entity;


import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HelpHistoryEntity {
    private long historyId;
    private long toMemberId;
    private long fromMemberId;
    private long categoryId;
    private String content;
    private double x;
    private double y;
    private Timestamp createdAt;
}

