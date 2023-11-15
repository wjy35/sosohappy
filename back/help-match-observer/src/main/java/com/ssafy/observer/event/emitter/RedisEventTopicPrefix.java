package com.ssafy.observer.event.emitter;

public enum RedisEventTopicPrefix {
    STATUS_CHANGE("statusChange"),
    MATCH_POP("matchPopEvent");

    private final String prefix;

    RedisEventTopicPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return this.prefix;
    }
}
