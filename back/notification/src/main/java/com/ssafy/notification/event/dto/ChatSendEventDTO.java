package com.ssafy.notification.event.dto;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class ChatSendEventDTO {
    Long sendMemberId;
    Long receiveMemberId;
    String content;
    Timestamp timestamp;
}
