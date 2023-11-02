package com.ssafy.help.match.db.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.help.match.db.entity.HelpMatchStatus;
import com.ssafy.help.match.db.entity.HelpMatchType;
import com.ssafy.help.match.db.entity.MemberSessionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.Map;


@Repository
@RequiredArgsConstructor
public class MemberSessionEntityRepository {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private final String PREFIX="memberSessionEntity:";

    public void create(Long memberId) {
        MemberSessionEntity memberSessionEntity = MemberSessionEntity
                .builder()
                .memberId(memberId)
                .matchType(HelpMatchType.None)
                .matchStatus(HelpMatchStatus.Default)
                .build();

        redisTemplate.opsForHash().putAll(PREFIX+memberSessionEntity.getMemberId(),objectMapper.convertValue(memberSessionEntity,Map.class));
    }

    public void connect(Long memberId,String sessionId){
        redisTemplate.opsForHash().put(PREFIX+memberId,"isConnected",true);
        redisTemplate.opsForHash().put(PREFIX+memberId,"sessionId",sessionId);
    }

    public void disconnect(Long memberId){
        redisTemplate.opsForHash().put(PREFIX+memberId,"isConnected",false);
        redisTemplate.opsForHash().put(PREFIX+memberId,"sessionId",null);
    }

    public void setMatchType(Long memberId, HelpMatchType matchType){
        redisTemplate.opsForHash().put(PREFIX+memberId,"matchType",matchType);
    }

    public void setMatchStatus(Long memberId, HelpMatchStatus matchStatus){
        redisTemplate.opsForHash().put(PREFIX+memberId,"matchStatus",matchStatus);
    }

    public HelpMatchType getMatchType(Long memberId){
        return (HelpMatchType) redisTemplate.opsForHash().get(PREFIX+memberId,"matchType");
    }

    public HelpMatchStatus getMatchStatus(Long memberId){
        return (HelpMatchStatus) redisTemplate.opsForHash().get(PREFIX+memberId,"matchStatus");
    }
}
