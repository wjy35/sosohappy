package com.ssafy.help.match.db.repository.impl;

import com.ssafy.help.match.db.entity.SendMatchEntity;
import com.ssafy.help.match.db.repository.SendMatchEntityRepository;
import com.ssafy.help.match.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class SendMatchEntityRepositoryImpl implements SendMatchEntityRepository {
    private final RedisTemplate<String,String> redisTemplate;
    private final ObjectSerializer objectSerializer;
    private final String PREFIX="matchEntity:";
    private final Long MAX_HELP_MATCH_SECOND=60l;


    @Override
    public void save(SendMatchEntity sendMatchEntity) {
        redisTemplate.opsForValue().set(PREFIX+ sendMatchEntity.getMemberId(),objectSerializer.serialize(sendMatchEntity),MAX_HELP_MATCH_SECOND, TimeUnit.SECONDS);
    }

    @Override
    public SendMatchEntity findByMemberId(Long memberId) {
        String sendMatchEntityStr = redisTemplate.opsForValue().get(PREFIX+memberId);

        SendMatchEntity sendMatchEntity = null;

        if(Optional.ofNullable(sendMatchEntityStr).isPresent()){
            sendMatchEntity =  objectSerializer.deserialize(sendMatchEntityStr, SendMatchEntity.class);
        }

        return sendMatchEntity;
    }


    @Override
    public SendMatchEntity getAndDeleteByMemberId(Long memberId) {
        String sendMatchEntityStr = redisTemplate.opsForValue().getAndDelete(PREFIX+memberId);

        SendMatchEntity sendMatchEntity = null;

        if(Optional.ofNullable(sendMatchEntityStr).isPresent()){
            sendMatchEntity =  objectSerializer.deserialize(sendMatchEntityStr, SendMatchEntity.class);
        }

        return sendMatchEntity;
    }
}
