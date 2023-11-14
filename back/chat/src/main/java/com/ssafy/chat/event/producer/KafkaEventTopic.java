package com.ssafy.chat.event.producer;

public enum KafkaEventTopic {
    CHAT_NOTIFICATION("chat.send");

    private final String topic;

    KafkaEventTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return topic;
    }
}
