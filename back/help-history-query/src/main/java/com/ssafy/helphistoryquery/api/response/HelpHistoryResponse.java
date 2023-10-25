package com.ssafy.helphistoryquery.api.response;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class HelpHistoryResponse {
    private long historyId;
    private long categoryId;
    private String categoryName;
    private String categoryImage;
    private String content;
    private Timestamp createdAt;
}
