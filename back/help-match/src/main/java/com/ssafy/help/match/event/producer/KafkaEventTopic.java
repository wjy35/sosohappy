package com.ssafy.help.match.event.producer;

public enum KafkaEventTopic {
    HELP_HISTORY_CREATE("help.history.create"),
    FORTUNE_COOKIE_USE("fortune-cookie.use");

    private final String topic;

    KafkaEventTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return topic;
    }
}
