package com.ssafy.helphistorysync.db.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.helphistorysync.db.entity.HelpCertificateEntity;
import com.ssafy.helphistorysync.db.entity.HelpHistoryEntity;
import com.ssafy.helphistorysync.db.repository.HelpHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class HelpHistoryRepositoryImpl implements HelpHistoryRepository {

    private final ObjectMapper objectMapper;

    private final ListOperations<String, Object> listOperations;

    @Override
    public void addHelpHistory(HelpHistoryEntity helpHistoryEntity) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(objectMapper.convertValue(helpHistoryEntity, Map.class));
        listOperations.rightPush("histories:toMemberId:" + helpHistoryEntity.getToMemberId(), json);
        listOperations.rightPush("histories:fromMemberId:" + helpHistoryEntity.getFromMemberId(), json);
    }

    @Override
    public void addHelpCertificate(HelpCertificateEntity helpCertificateEntity, long memberId) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(objectMapper.convertValue(helpCertificateEntity, Map.class));
        listOperations.rightPush("certificate:memberId:"+memberId,json);
    }
}
