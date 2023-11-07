package com.ssafy.chat.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatPayload {
    private Integer roomId;
    private Long sendMemberId;
    private Long receiveMemberId;
    private String content;
}
