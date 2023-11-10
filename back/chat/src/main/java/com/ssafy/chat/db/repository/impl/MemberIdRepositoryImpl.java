package com.ssafy.chat.db.repository.impl;

import com.ssafy.chat.db.repository.MemberIdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberIdRepositoryImpl implements MemberIdRepository {

    @Value("${MEMBER_ID_REPOSITORY_KEY_PREFIX}")
    private String simpSessionIdPrefix;

    private final SetOperations<String, Long> setOperations;

    public void saveBySimpSessionId(String simpSessionId, Long memberId){
        setOperations.add(simpSessionIdPrefix+simpSessionId, memberId);
    }

    public Long deleteAndGetBySimpSessionId(String simpSessionId){
        return setOperations.pop(simpSessionIdPrefix+simpSessionId);
    }

}
