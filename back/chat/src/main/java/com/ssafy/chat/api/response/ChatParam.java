package com.ssafy.chat.api.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatParam {
    private int memberId;
    private String roomId;
    private String message;
}
