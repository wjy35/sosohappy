package com.ssafy.chat.db.repository;

import com.ssafy.chat.db.entity.ChatRoomEntity;
import com.ssafy.chat.db.entity.ChatRoomEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, ChatRoomEntityPK> {

}
