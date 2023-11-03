package com.ssafy.help.match.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RedisUUID {
    @Value("${spring.server.uuid}")
    public String uuid;

    public String get() {
        return uuid;
    }
}
