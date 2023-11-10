package com.ssafy.chat.api.request;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ChatPayload {
    private int chatRoomId;
    private Long sendMemberId;
    private Long receiveMemberId;
    private Long type;
    private String content;
    @Builder.Default
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    @Builder
    public ChatPayload(int chatRoomId, Long sendMemberId, Long receiveMemberId, Long type, String content) {
        this.chatRoomId = chatRoomId;
        this.sendMemberId = sendMemberId;
        this.receiveMemberId = receiveMemberId;
        this.type = type;
        this.content = content;
    }
}
