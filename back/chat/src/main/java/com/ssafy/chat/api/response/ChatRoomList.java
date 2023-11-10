package com.ssafy.chat.api.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChatRoomList {
    private int chatRoomId;
    private List<Long> memberList;
    private CurrentChat currentChat;
}
