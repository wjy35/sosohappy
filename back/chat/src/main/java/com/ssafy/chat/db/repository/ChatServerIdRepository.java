package com.ssafy.chat.db.repository;

public interface ChatServerIdRepository {
    void saveByMemberId(Long memberId);

    void deleteByMemberId(Long memberId);

    String getChatServerIdByMemberId(Long memberId);
}
