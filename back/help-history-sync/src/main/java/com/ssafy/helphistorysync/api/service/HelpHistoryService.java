package com.ssafy.helphistorysync.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.helphistorysync.api.dto.CategoryDto;
import com.ssafy.helphistorysync.api.dto.MemberDto;
import com.ssafy.helphistorysync.api.request.HelpHistoryRequest;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface HelpHistoryService {

    void addHelpHistory(ConsumerRecord<String,String> message) throws JsonProcessingException;

    HelpHistoryRequest getAfter(ConsumerRecord<String,String> message) throws JsonProcessingException;

    CategoryDto getCategory(HelpHistoryRequest helpHistoryRequest) throws JsonProcessingException;
}
