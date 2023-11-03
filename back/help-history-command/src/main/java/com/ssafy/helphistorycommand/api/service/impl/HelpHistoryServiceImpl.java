package com.ssafy.helphistorycommand.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.helphistorycommand.api.mapper.HelpHistoryMapper;
import com.ssafy.helphistorycommand.api.request.HelpHistoryRequest;
import com.ssafy.helphistorycommand.db.repository.HelpHistoryRepository;
import com.ssafy.helphistorycommand.api.service.HelpHistoryService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelpHistoryServiceImpl implements HelpHistoryService {

    private final HelpHistoryRepository helpHistoryRepository;

    private final ObjectMapper objectMapper;

    private final HelpHistoryMapper helpHistoryMapper;

    @Override
    public void addHelpHistory(ConsumerRecord<String,String> message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message.value());
            HelpHistoryRequest request = objectMapper.treeToValue(jsonNode.get("after"), HelpHistoryRequest.class);
//            helpHistoryRepository.save(helpHistoryMapper.requestToEntity(request));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
