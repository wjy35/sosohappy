package com.ssafy.helphistorysync.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.helphistorysync.api.mapper.HelpHistoryMapper;
import com.ssafy.helphistorysync.api.request.HelpHistoryRequest;
import com.ssafy.helphistorysync.api.service.HelpHistoryService;
import com.ssafy.helphistorysync.db.entity.HelpHistoryEntity;
import com.ssafy.helphistorysync.db.repository.HelpHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelpHistoryServiceImpl implements HelpHistoryService {

    private final ObjectMapper objectMapper;

    private final HelpHistoryMapper helpHistoryMapper;

    private final HelpHistoryRepository helpHistoryRepository;

    @Override
    public void addHelpHistory(ConsumerRecord<String, String> message) throws JsonProcessingException {

        HelpHistoryRequest helpHistoryRequest = getAfter(message);

        HelpHistoryEntity helpHistoryEntity = helpHistoryMapper.requestToEntity(helpHistoryRequest);

        helpHistoryRepository.addHelpHistory(helpHistoryEntity);
    }

    @Override
    public HelpHistoryRequest getAfter(ConsumerRecord<String, String> message) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(message.value());
        return objectMapper.treeToValue(jsonNode.get("after"), HelpHistoryRequest.class);
    }
}
