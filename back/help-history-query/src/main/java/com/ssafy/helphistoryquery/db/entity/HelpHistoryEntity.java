package com.ssafy.helphistoryquery.db.entity;

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
    private String categoryName;
    private String categoryImage;
    private String content;
    private double x;
    private double y;
    private Timestamp createdAt;

}

