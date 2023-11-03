package com.ssafy.help.match.db.repository.impl;

import com.ssafy.help.match.db.entity.HelpEntity;
import com.ssafy.help.match.db.repository.HelpEntityRepository;
import com.ssafy.help.match.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class HelpEntityRepositoryImpl implements HelpEntityRepository {
    private final RedisTemplate<String,String> redisTemplate;
    private final ObjectSerializer objectSerializer;
    private final String PREFIX="helpEntity:";
    private final Long MAX_HELP_MATCH_SECOND=60l;


    @Override
    public void save(HelpEntity helpEntity) {
        redisTemplate.opsForValue().set(PREFIX+helpEntity.getMemberId(),objectSerializer.serialize(helpEntity),MAX_HELP_MATCH_SECOND, TimeUnit.SECONDS);
    }

    @Override
    public HelpEntity findByMemberId(Long memberId) {
        return objectSerializer.deserialize(redisTemplate.opsForValue().get(PREFIX+memberId), HelpEntity.class);
    }

    @Override
    public boolean existByMemberId(Long memberId) {
        return redisTemplate.hasKey(PREFIX+memberId);
    }
}
