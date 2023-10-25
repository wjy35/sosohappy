package com.ssafy.helphistoryquery.api.response;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class ReceiveHelpHistoryResponse {
    private long historyId;
    private long fromMemberId;
    private String fromMemberName;
    private long categoryId;
    private String categoryImage;
    private String content;
    private Timestamp createdAt;
}
