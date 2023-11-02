package com.ssafy.helphistorysync.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.helphistorysync.api.request.HelpHistoryRequest;
import com.ssafy.helphistorysync.api.service.HelpHistoryService;
import com.ssafy.helphistorysync.cloud.feign.CategoryFeign;
import com.ssafy.helphistorysync.db.repository.HelpHistoryRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("local")
class HelpHistoryServiceImplTest {

    @Autowired
    private HelpHistoryService helpHistoryService;
    @Autowired
    private HelpHistoryRepository helpHistoryRepository;
    @Autowired
    private CategoryFeign categoryFeign;
    @Autowired
    private ObjectMapper objectMapper;

    @DisplayName("ConsumerRecord 객체로부터 after의 정보를 출력해 HelpHistoryRequest에 매핑한다.")
    @Test
    void getAfter() {

        //given
        String jsonString = "{\n" +
                "  \"before\":null,\n" +
                "  \"after\":{\n" +
                "    \"history_id\":33,\n" +
                "    \"category_id\":33,\n" +
                "    \"content\":\"test33\",\n" +
                "    \"created_at\":\"2023-10-31T06:53:44Z\",\n" +
                "    \"from_member_id\":1,\n" +
                "    \"to_member_id\":2,\n" +
                "    \"x\":12.333,\n" +
                "    \"y\":23.534\n" +
                "  }\n" +
                "}";

        HelpHistoryRequest helpHistoryRequest;
        ConsumerRecord<String, String> message = new ConsumerRecord<>("topicName", 0, 0, "key", jsonString);
        JsonNode jsonNode;

        // when

        try {
            jsonNode = objectMapper.readTree(message.value());
            helpHistoryRequest = objectMapper.treeToValue(jsonNode.get("after"), HelpHistoryRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // then
        assertThat(helpHistoryRequest).isNotNull();
        assertThat(helpHistoryRequest.getHistoryId()).isEqualTo(33);

    }

    @DisplayName("jsonparsing 오류 시 JsonProcessingException 예외를 발생시킨다.")
    @Test
    void invalidJsonProcessing() {

        // given
        String invalidJson = "invalidJson";

        // when / then
        assertThatThrownBy(() -> {
            objectMapper.readTree(invalidJson);
        }).isInstanceOf(JsonProcessingException.class);
    }
}