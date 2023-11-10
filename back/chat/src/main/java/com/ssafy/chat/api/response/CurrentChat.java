package com.ssafy.chat.api.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CurrentChat {
    private int type;
    private long memberId;
    private String content;
    private Timestamp timestamp;
}
