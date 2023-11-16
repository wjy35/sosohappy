package com.ssafy.chat.api.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class CurrentChat {
    private int type;
    private long memberId;
    private String content;
    private Timestamp timestamp;
}
