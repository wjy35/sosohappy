package com.ssafy.helphistorycommand.api.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface HelpHistoryService {

    void addHelpHistory(ConsumerRecord<String,String> message);
}
