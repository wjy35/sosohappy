package com.ssafy.observer.db.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;

import com.ssafy.observer.db.repository.SendMatchEntityRepository;
import com.ssafy.observer.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class SendMatchEntityRepositoryImpl implements SendMatchEntityRepository {
    private final RedisTemplate<String,String> redisTemplate;
    private final ObjectSerializer objectSerializer;
    private final String RECEIVE_MEMBER_ID_SET_PREFIX="receiveMemberIdSet:";

    @Override
    public Set<Long> getAndDeleteReceiveMemberIdSet(Long sendMemberId) {
        String receiveMemberIdSetStr = redisTemplate.opsForValue().getAndDelete(RECEIVE_MEMBER_ID_SET_PREFIX+sendMemberId);
        return objectSerializer.deserialize(receiveMemberIdSetStr,new TypeReference<Set<Long>>(){});
    }
}
