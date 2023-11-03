package com.ssafy.chat.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chatroom")
@IdClass(ChatRoomEntityPK.class)
public class ChatRoomEntity {
    @Column(name = "chat_room_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatRoomId;

    @Id
    @Column(name = "sender_member_id")
    private Long senderMemberId;

    @Id
    @Column(name = "receiver_member_id")
    private Long receiverMemberId;

    @Builder
    public ChatRoomEntity(Long senderMemberId, Long receiverMemberId) {
        this.senderMemberId = senderMemberId;
        this.receiverMemberId = receiverMemberId;
    }
}
