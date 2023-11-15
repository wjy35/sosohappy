package com.ssafy.observer.event.emitter;

import com.ssafy.observer.event.dto.EventDTO;
import com.ssafy.observer.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisEventEmitter {
    private final RedisTemplate<String,String> redisTemplate;
    private final ObjectSerializer objectSerializer;

    public void emit(RedisEventTopicPrefix redisEventTopicPrefix, String uuid, EventDTO eventDTO){
        redisTemplate.convertAndSend(redisEventTopicPrefix+uuid,objectSerializer.serialize(eventDTO));
    }

    public void emit(RedisEventTopicPrefix redisEventTopicPrefix, String uuid, String eventMessage){
        redisTemplate.convertAndSend(redisEventTopicPrefix+uuid,eventMessage);
    }
}
