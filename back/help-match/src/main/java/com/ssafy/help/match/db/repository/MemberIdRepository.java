package com.ssafy.help.match.db.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberIdRepository {
    private final RedisTemplate<String,String> redisTemplate;
    private final String PREFIX="sessionId:";

    public void save(String sessionId,Long memberId){
        redisTemplate.opsForValue().set(PREFIX+sessionId, memberId.toString());
    }

    public Long getMemberId(String sessionId){
        return Long.parseLong(redisTemplate.opsForValue().get(PREFIX+sessionId));
    }

    public Long deleteAndGetMemberId(String sessionId){
        return Long.parseLong(redisTemplate.opsForValue().getAndDelete(PREFIX+sessionId));
    }
}
