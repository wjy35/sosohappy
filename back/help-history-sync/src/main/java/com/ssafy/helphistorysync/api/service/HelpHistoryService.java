package com.ssafy.helphistorysync.api.service;

import com.ssafy.helphistorysync.api.request.HelpHistoryRequest;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface HelpHistoryService {

    void addHelpHistory(ConsumerRecord<String,String> message);

    HelpHistoryRequest getAfter(ConsumerRecord<String,String> message);
}
