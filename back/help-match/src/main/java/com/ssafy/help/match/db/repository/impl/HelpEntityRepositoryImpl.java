package com.ssafy.help.match.db.repository.impl;

import com.ssafy.help.match.db.entity.HelpEntity;
import com.ssafy.help.match.db.repository.HelpEntityRepository;
import com.ssafy.help.match.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HelpEntityRepositoryImpl implements HelpEntityRepository {
    private final ObjectSerializer objectSerializer;
    private final RedisTemplate<String,String> redisTemplate;
    private final String PREFIX="helpEntity:";

    @Override
    public void save(Long memberId, HelpEntity helpEntity) {
        redisTemplate.opsForValue().set(PREFIX+memberId, objectSerializer.serialize(helpEntity));
    }

    @Override
    public HelpEntity getAndDeleteByMemberId(Long memberId) {
        return objectSerializer.deserialize(redisTemplate.opsForValue().getAndDelete(PREFIX+memberId), HelpEntity.class);
    }

    @Override
    public HelpEntity findByMemberId(Long memberId) {
        return objectSerializer.deserialize(redisTemplate.opsForValue().get(PREFIX+memberId), HelpEntity.class);
    }
}
