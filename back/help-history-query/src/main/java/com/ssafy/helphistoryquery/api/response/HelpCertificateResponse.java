package com.ssafy.helphistoryquery.api.response;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class HelpCertificateResponse {
    private String nickName;
    private String categoryName;
    private Timestamp createdAt;
}
