package com.ssafy.chat.api.request;

import lombok.Getter;

@Getter
public class ChatRoomCreateRequest {
    long senderMemberId;
    long receiverMemberId;
}
