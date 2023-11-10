package com.ssafy.chat.db.repository;

public interface MemberIdRepository {

    void saveBySimpSessionId(String simpSessionId, Long memberId);

    Long deleteAndGetBySimpSessionId(String simpSessionId);
}
