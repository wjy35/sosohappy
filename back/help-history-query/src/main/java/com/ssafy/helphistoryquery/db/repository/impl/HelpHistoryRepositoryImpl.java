package com.ssafy.helphistoryquery.db.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.helphistoryquery.api.response.ReceiveHelpHistoryResponse;
import com.ssafy.helphistoryquery.db.entity.HelpHistoryEntity;
import com.ssafy.helphistoryquery.db.repository.HelpHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Repository
@RequiredArgsConstructor
public class HelpHistoryRepositoryImpl implements HelpHistoryRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ListOperations<String, Object> listOps;

    private final ObjectMapper objectMapper;

    @Override
    public Integer getHelpCount(Long memberId) throws JsonProcessingException {
               return Math.toIntExact(Optional.ofNullable(listOps.size("histories:toMemberId:" + memberId))
                .orElseThrow(RuntimeException::new));
    }

    @Override
    public List<HelpHistoryEntity> getHelpReceivedList(Long memberId) {
        List<Object> objectList = listOps.getOperations().opsForList().range("histories:toMemberId:" + memberId, 0, -1);
        return this.getHelpHistoryEntityList(objectList);
    }

    @Override
    public List<HelpHistoryEntity> getHelpHistoryEntityList(List<Object> objectList){
        List<HelpHistoryEntity> helpHistoryList = new ArrayList<>();

        for (Object json : Objects.requireNonNull(objectList)) {
            try {
                HelpHistoryEntity helpHistory = objectMapper.readValue((String) json, HelpHistoryEntity.class);
                helpHistoryList.add(helpHistory);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return helpHistoryList;
    }
}
