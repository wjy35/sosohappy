package com.ssafy.observer.db.repository.impl;

import com.ssafy.observer.db.entity.HelpMatchStatus;
import com.ssafy.observer.db.entity.HelpMatchType;
import com.ssafy.observer.db.repository.MemberSessionEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberSessionEntityRepositoryImpl implements MemberSessionEntityRepository {
    private final RedisTemplate<String, Object> redisTemplate;
    private final String PREFIX="memberSessionEntity:";


    @Override
    public void setMatchType(Long memberId, HelpMatchType matchType){
        redisTemplate.opsForHash().put(PREFIX+memberId,"matchType",matchType);
    }

    @Override
    public void setMatchStatus(Long memberId, HelpMatchStatus matchStatus){
        redisTemplate.opsForHash().put(PREFIX+memberId,"matchStatus",matchStatus);
    }

    @Override
    public String getServerUUID(Long memberId){
        return (String)redisTemplate.opsForHash().get(PREFIX+memberId,"serverUUID");
    }

    @Override
    public boolean isConnected(Long memberId){
        return (boolean)redisTemplate.opsForHash().get(PREFIX+memberId,"isConnected");
    }
}
