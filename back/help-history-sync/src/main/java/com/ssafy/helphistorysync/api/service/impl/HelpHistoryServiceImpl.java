package com.ssafy.helphistorysync.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.helphistorysync.api.mapper.HelpHistoryMapper;
import com.ssafy.helphistorysync.api.dto.CategoryDto;
import com.ssafy.helphistorysync.api.request.HelpHistoryRequest;
import com.ssafy.helphistorysync.api.service.HelpHistoryService;
import com.ssafy.helphistorysync.cloud.feign.CategoryFeign;
import com.ssafy.helphistorysync.db.entity.HelpHistoryEntity;
import com.ssafy.helphistorysync.db.repository.HelpHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelpHistoryServiceImpl implements HelpHistoryService {

    private final ObjectMapper objectMapper;

    private final HelpHistoryMapper helpHistoryMapper;

    private final HelpHistoryRepository helpHistoryRepository;

    private final CategoryFeign categoryFeign;


    @Override
    public void addHelpHistory(ConsumerRecord<String, String> message) throws JsonProcessingException {

        HelpHistoryRequest helpHistoryRequest = getAfter(message);

        if(helpHistoryRequest == null) return;

        CategoryDto categoryDto = getCategory(helpHistoryRequest);

        HelpHistoryEntity helpHistoryEntity = helpHistoryMapper.requestToEntity(helpHistoryRequest, categoryDto);

        helpHistoryRepository.addHelpHistory(helpHistoryEntity);

    }

    @Override
    public HelpHistoryRequest getAfter(ConsumerRecord<String, String> message) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(message.value());
        return objectMapper.treeToValue(jsonNode.get("after"), HelpHistoryRequest.class);
    }

    @Override
    public CategoryDto getCategory(HelpHistoryRequest helpHistoryRequest) {
        String jsonString = categoryFeign.getCategoryDetail(helpHistoryRequest.getCategoryId());

        JSONObject jsonObject = new JSONObject(jsonString).getJSONObject("result").getJSONObject("category");

        long categoryId = jsonObject.getLong("categoryId");
        String categoryName = jsonObject.getString("categoryName");
        String categoryImage = jsonObject.getString("categoryImage");

        return CategoryDto.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .categoryImage(categoryImage).build();
    }

}
