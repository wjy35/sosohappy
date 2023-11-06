package com.ssafy.chat.api.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ChatRoomListParam {
    private int chatRoomId;
    private long chatPartnerMemberId;
    private String content;
    private Timestamp timestamp;
}
