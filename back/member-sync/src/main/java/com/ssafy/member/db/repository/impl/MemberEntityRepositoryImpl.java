package com.ssafy.member.db.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.member.db.entity.MemberEntity;
import com.ssafy.member.db.repository.MemberEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class MemberEntityRepositoryImpl implements MemberEntityRepository {
    private final HashOperations<Long,Object,Object> entityOperations;
    private final SetOperations<String,Object> uniqueFieldValueSetOperations;
    private final ObjectMapper objectMapper;

    @Override
    public void save(MemberEntity memberEntity) {
        entityOperations.putAll(memberEntity.getMemberId(),objectMapper.convertValue(memberEntity, Map.class));
        uniqueFieldValueSetOperations.add("memberSignId",memberEntity.getMemberSignId());
        uniqueFieldValueSetOperations.add("nickname",memberEntity.getNickname());
    }
}
