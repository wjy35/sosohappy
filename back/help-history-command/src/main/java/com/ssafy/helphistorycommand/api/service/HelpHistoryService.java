package com.ssafy.helphistorycommand.api.service;

import com.ssafy.helphistorycommand.db.entity.HelpHistoryEntity;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface HelpHistoryService {

    void addHelpHistory(ConsumerRecord<String,String> message);

    void addHelpCertificate(HelpHistoryEntity helpHistory);
}
