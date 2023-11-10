package com.ssafy.chat.db.entity;

import lombok.*;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chatroom")
@IdClass(ChatRoomEntityPK.class)
public class ChatRoomEntity {

    @Column(name = "chat_room_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.hibernate.annotations.Generated(GenerationTime.INSERT)
    private Integer chatRoomId;

    @Id
    @Column(name = "sender_member_id")
    private Long senderMemberId;

    @Id
    @Column(name = "receiver_member_id")
    private Long receiverMemberId;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @org.hibernate.annotations.Generated(GenerationTime.INSERT)
    private Timestamp createdAt;

    @Builder
    public ChatRoomEntity(Long senderMemberId, Long receiverMemberId) {
        this.senderMemberId = senderMemberId;
        this.receiverMemberId = receiverMemberId;
    }
}
