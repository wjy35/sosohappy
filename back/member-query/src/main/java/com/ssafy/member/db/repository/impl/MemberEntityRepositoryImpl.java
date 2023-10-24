package com.ssafy.member.db.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.member.db.entity.MemberEntity;
import com.ssafy.member.db.repository.MemberEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MemberEntityRepositoryImpl implements MemberEntityRepository {
    private final RedisTemplate<Long, Map<String,Object>> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public MemberEntity findByMemberId(Long memberId) {
        return objectMapper.convertValue(redisTemplate.opsForHash().entries(memberId), MemberEntity.class);
    }
}
