package com.ssafy.help.match.db.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.help.match.config.RedisUUID;
import com.ssafy.help.match.db.entity.HelpMatchStatus;
import com.ssafy.help.match.db.entity.HelpMatchType;
import com.ssafy.help.match.db.entity.MemberSessionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.Map;


@Repository
@RequiredArgsConstructor
public class MemberSessionEntityRepository {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private final String PREFIX="memberSessionEntity:";
    private final RedisUUID redisUUID;

    public void create(Long memberId) {
        MemberSessionEntity memberSessionEntity = MemberSessionEntity
                .builder()
                .memberId(memberId)
                .matchType(HelpMatchType.NONE)
                .matchStatus(HelpMatchStatus.DEFAULT)
                .isConnected(false)
                .sessionId("")
                .serverUUID("")
                .build();

        redisTemplate.opsForHash().putAll(PREFIX+memberSessionEntity.getMemberId(),objectMapper.convertValue(memberSessionEntity,Map.class));
    }

    public void connect(Long memberId,String sessionId){
        redisTemplate.opsForHash().put(PREFIX+memberId,"isConnected",true);
        redisTemplate.opsForHash().put(PREFIX+memberId,"sessionId",sessionId);
        redisTemplate.opsForHash().put(PREFIX+memberId,"serverUUID",redisUUID.get());
    }

    public void disconnect(Long memberId){
        redisTemplate.opsForHash().put(PREFIX+memberId,"isConnected",false);
        redisTemplate.opsForHash().put(PREFIX+memberId,"sessionId","");
        redisTemplate.opsForHash().put(PREFIX+memberId,"serverUUID","");
    }

    public void setMatchType(Long memberId, HelpMatchType matchType){
        redisTemplate.opsForHash().put(PREFIX+memberId,"matchType",matchType);
    }

    public void setMatchStatus(Long memberId, HelpMatchStatus matchStatus){
        redisTemplate.opsForHash().put(PREFIX+memberId,"matchStatus",matchStatus);
    }

    public HelpMatchType getMatchType(Long memberId){
        return HelpMatchType.valueOf((String)redisTemplate.opsForHash().get(PREFIX+memberId,"matchType"));
    }

    public HelpMatchStatus getMatchStatus(Long memberId){
        return HelpMatchStatus.valueOf((String)redisTemplate.opsForHash().get(PREFIX+memberId,"matchStatus"));
    }

    public String getServerUUID(Long memberId){
        return (String)redisTemplate.opsForHash().get(PREFIX+memberId,"serverUUID");
    }

    public boolean isConnected(Long memberId){
        return (boolean)redisTemplate.opsForHash().get(PREFIX+memberId,"isConnected");
    }
}
