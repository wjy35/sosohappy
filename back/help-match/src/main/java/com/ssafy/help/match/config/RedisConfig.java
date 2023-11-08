package com.ssafy.help.match.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfig {
    @Value("${REDIS_HOST}")
    public String host;

    @Value("${REDIS_PORT}")
    public int port;

    @Value("${REDIS_PASSWORD}")
    public String password;



    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        redisStandaloneConfiguration.setPassword(password);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        redisTemplate.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        return redisTemplate;
    }

    @Bean
    public SetOperations<String, Long> setOperations() {
        RedisTemplate<String, Long> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());

        redisTemplate.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericToStringSerializer<Long>(Long.class));
        redisTemplate.afterPropertiesSet();

        return redisTemplate.opsForSet();
    }

    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(
            StatusChangeEventListener statusChangeEventListener,
            MatchPushEventListener matchPushEventListener,
            MatchPopEventListener matchPopEventListener,
            RedisUUID redisUUID
    ) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory());
        container.addMessageListener(matchPushEventListener, new ChannelTopic("matchPushEvent:"+redisUUID.get()));
        container.addMessageListener(matchPopEventListener, new ChannelTopic("matchPopEvent:"+redisUUID.get()));
        container.addMessageListener(statusChangeEventListener, new ChannelTopic("statusChangeEvent:"+redisUUID.get()));
        return container;
    }

}
