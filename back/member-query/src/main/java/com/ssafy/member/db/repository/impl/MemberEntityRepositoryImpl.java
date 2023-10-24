package com.ssafy.member.db.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.member.db.entity.MemberEntity;
import com.ssafy.member.db.repository.MemberEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class MemberEntityRepositoryImpl implements MemberEntityRepository {
    private final RedisTemplate<Long, Object> entityRedisTemplate;
    private final RedisTemplate<String, Object> uniqueFieldValueSetRedisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public MemberEntity findByMemberId(Long memberId) {
        return objectMapper.convertValue(entityRedisTemplate.opsForHash().entries(memberId), MemberEntity.class);
    }

    @Override
    public Boolean existsByNickname(String nickname) {
        return uniqueFieldValueSetRedisTemplate.opsForSet().isMember("nickname",nickname);
    }

}
