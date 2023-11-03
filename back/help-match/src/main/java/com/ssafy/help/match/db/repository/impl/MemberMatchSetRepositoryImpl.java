package com.ssafy.help.match.db.repository.impl;

import com.ssafy.help.match.db.repository.MemberMatchSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@RequiredArgsConstructor
public class MemberMatchSetRepositoryImpl implements MemberMatchSetRepository {
    private final RedisTemplate<String,String> redisTemplate;
    private final String PREFIX="matchMemberSet:";

    @Override
    public void save(Long memberId, Long matchedMember) {
        redisTemplate.opsForSet().add(PREFIX+memberId,matchedMember.toString());
    }

    @Override
    public void delete(Long memberId, Long matchedMember) {
        redisTemplate.opsForSet().remove(PREFIX+memberId,matchedMember.toString());
    }

    @Override
    public Set<String> getSet(Long memberId) {
        return redisTemplate.opsForSet().members(PREFIX+memberId);
    }
}
