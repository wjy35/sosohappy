package com.ssafy.chat.db.repository.impl;

import com.ssafy.chat.config.ChatServerInfo;
import com.ssafy.chat.db.repository.ChatServerIdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ChatServerIdRepositoryImpl implements ChatServerIdRepository {
    @Value("${CHAT_SERVER_ID_REPOSITORY_PREFIX}")
    String chatServerIdPrefix;

    private final ChatServerInfo chatServerInfo;

    private final RedisTemplate<String, String> redisTemplate;

    public void saveByMemberId(Long memberId){
        redisTemplate.opsForValue().set(chatServerIdPrefix+memberId, chatServerInfo.getId());
    }

    public void deleteByMemberId(Long memberId){
        redisTemplate.opsForValue().getAndDelete(chatServerIdPrefix+memberId);
    }

    public String getChatServerIdByMemberId(Long memberId){
        return redisTemplate.opsForValue().get(chatServerIdPrefix+memberId);
    }
}
