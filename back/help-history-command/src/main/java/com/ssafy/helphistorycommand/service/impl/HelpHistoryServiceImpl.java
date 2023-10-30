package com.ssafy.helphistorycommand.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.helphistorycommand.api.request.HelpHistoryRequest;
import com.ssafy.helphistorycommand.db.repository.HelpHistoryRepository;
import com.ssafy.helphistorycommand.service.HelpHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelpHistoryServiceImpl implements HelpHistoryService {

    private final HelpHistoryRepository helpHistoryRepository;

    private final ObjectMapper objectMapper;

    @Override
    public void addHelpHistory(String message) {
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            HelpHistoryRequest request = objectMapper.treeToValue(jsonNode.get("after"), HelpHistoryRequest.class);

//            System.out.println(request.toString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // 글고 그걸 HelpHistoryEntity로 만들어

        // 저장해
//        helpHistoryRepository.save(helpHistoryEntity);
    }
}
