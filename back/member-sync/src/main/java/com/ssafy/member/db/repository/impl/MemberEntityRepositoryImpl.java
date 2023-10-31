package com.ssafy.member.db.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.member.db.entity.MemberEntity;
import com.ssafy.member.db.repository.MemberEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MemberEntityRepositoryImpl implements MemberEntityRepository {
    private final HashOperations<Long,Object,Object> entityOperations;
    private final SetOperations<String,Object> uniqueFieldValueSetOperations;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void save(MemberEntity memberEntity) {
        entityOperations.putAll(memberEntity.getMemberId(),objectMapper.convertValue(memberEntity, Map.class));
        uniqueFieldValueSetOperations.add("memberSignId",memberEntity.getMemberSignId());
        uniqueFieldValueSetOperations.add("nickname",memberEntity.getNickname());
    }

    @Override
    @Transactional
    public void update(MemberEntity before, MemberEntity after) {
        if(!before.getNickname().equals(after.getNickname())){
            uniqueFieldValueSetOperations.remove("nickname",before.getNickname());
            uniqueFieldValueSetOperations.add("nickname",after.getNickname());
        }

        if(!before.getMemberSignId().equals(after.getMemberSignId())){
            uniqueFieldValueSetOperations.remove("memberSignId",before.getMemberSignId());
            uniqueFieldValueSetOperations.add("memberSignId",after.getMemberSignId());
        }

        entityOperations.putAll(after.getMemberId(),objectMapper.convertValue(after, Map.class));
    }

}
