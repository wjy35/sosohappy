package com.ssafy.chat.db.entity;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ChatEntity {
    private Long sendMemberId;
    private Long receiveMemberId;
    private int type;
    private String content;
    @Builder.Default
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
}
