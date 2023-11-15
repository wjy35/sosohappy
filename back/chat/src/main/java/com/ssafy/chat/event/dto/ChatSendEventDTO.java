package com.ssafy.chat.event.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ChatSendEventDTO {
    Long sendMemberId;
    Long receiveMemberId;
    String content;
    Timestamp timestamp;
}
