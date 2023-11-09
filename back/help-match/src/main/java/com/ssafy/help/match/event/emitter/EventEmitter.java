package com.ssafy.help.match.event.emitter;

import com.ssafy.help.match.config.RedisUUID;
import com.ssafy.help.match.event.dto.EventDTO;
import com.ssafy.help.match.util.ObjectSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventEmitter {
    private final RedisUUID redisUUID;
    private final RedisTemplate<String,String> redisTemplate;
    private final ObjectSerializer objectSerializer;

    @Async
    public void emit(EventTopicPrefix eventTopicPrefix,EventDTO eventDTO){
        redisTemplate.convertAndSend(eventTopicPrefix+redisUUID.get(),objectSerializer.serialize(eventDTO));
    }

    @Async
    public void emit(EventTopicPrefix eventTopicPrefix,String eventMessage){
        redisTemplate.convertAndSend(eventTopicPrefix+redisUUID.get(),eventMessage);
    }
}
