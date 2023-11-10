
package com.ssafy.chat.api.request;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class ChatPublish implements Serializable {
    private Integer chatRoomId;
    private Long sendMemberId;
    private Long receiveMemberId;
    private String content;
    @Builder.Default
    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    @Builder
    public ChatPublish(Integer chatRoomId, Long sendMemberId, Long receiveMemberId, String content, Timestamp timestamp) {
        this.chatRoomId = chatRoomId;
        this.sendMemberId = sendMemberId;
        this.receiveMemberId = receiveMemberId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getChatRoomDetailDestination(){
        return "/topic/chat";
    }

    public String getChatRoomListDestination(){
        return "/topic/"+this.receiveMemberId;
    }
}
