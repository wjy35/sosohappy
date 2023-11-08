package com.ssafy.chat.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatPayload {
    private int roomId;
    private Long memberId;
    private Long type;
    private String content;
}
