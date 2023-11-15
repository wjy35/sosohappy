package com.ssafy.helphistorysync.db.entity;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HelpCertificateEntity {
    private long historyId;
    private String nickName;
    private String categoryName;
    private Timestamp createdAt;
}
