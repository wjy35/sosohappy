package com.ssafy.helphistorysync.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.helphistorysync.api.dto.MemberDto;
import com.ssafy.helphistorysync.api.mapper.HelpHistoryMapper;
import com.ssafy.helphistorysync.api.dto.CategoryDto;
import com.ssafy.helphistorysync.api.request.HelpHistoryRequest;
import com.ssafy.helphistorysync.api.service.HelpHistoryService;
import com.ssafy.helphistorysync.cloud.feign.CategoryFeign;
import com.ssafy.helphistorysync.cloud.feign.MemberFeign;
import com.ssafy.helphistorysync.db.entity.HelpCertificateEntity;
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

    private final MemberFeign memberFeign;

    @Override
    public void addHelpHistory(ConsumerRecord<String, String> message) throws JsonProcessingException {

        HelpHistoryRequest helpHistoryRequest = getAfter(message);

        CategoryDto categoryDto = getCategory(helpHistoryRequest);

        MemberDto memberDto = getMember(helpHistoryRequest.getToMemberId());

        HelpHistoryEntity helpHistoryEntity = helpHistoryMapper.requestToEntity(helpHistoryRequest, categoryDto);

        HelpCertificateEntity helpCertificateEntity = HelpCertificateEntity.builder()
                .historyId(helpHistoryEntity.getHistoryId())
                .createdAt(helpHistoryRequest.getCreatedAt())
                .nickName(memberDto.getNickName())
                .categoryName(categoryDto.getCategoryName())
                .build();

        helpHistoryRepository.addHelpHistory(helpHistoryEntity);

        helpHistoryRepository.addHelpCertificate(helpCertificateEntity, memberDto.getMemberId());
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

    @Override
    public MemberDto getMember(long memberId) {
        String jsonString = memberFeign.getMemberDetail(memberId);
        JSONObject jsonObject = new JSONObject(jsonString).getJSONObject("result").getJSONObject("member");

        return MemberDto.builder()
                .memberId(memberId)
                .nickName(jsonObject.getString("nickname")).build();
    }


}
