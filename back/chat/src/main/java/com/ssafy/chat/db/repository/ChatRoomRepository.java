package com.ssafy.chat.db.repository;

import com.ssafy.chat.db.entity.ChatRoomEntity;
import com.ssafy.chat.db.entity.ChatRoomEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, ChatRoomEntityPK> {
    @Query("SELECT c FROM ChatRoomEntity c WHERE (c.senderMemberId = :senderMemberId AND c.receiverMemberId = :receiverMemberId) OR (c.receiverMemberId = :senderMemberId AND c.senderMemberId = :receiverMemberId)")
    Optional<ChatRoomEntity> findByChatRoomByUserIds(@Param("senderMemberId") Long senderMemberId, @Param("receiverMemberId") Long receiverMemberId);

    List<ChatRoomEntity> findByReceiverMemberIdOrSenderMemberId(long receiverMemberId, long senderMemberId);
}
