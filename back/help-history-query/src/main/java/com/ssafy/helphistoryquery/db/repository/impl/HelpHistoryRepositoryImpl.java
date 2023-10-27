package com.ssafy.helphistoryquery.db.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.helphistoryquery.db.entity.HelpHistoryEntity;
import com.ssafy.helphistoryquery.db.repository.HelpHistoryRepository;
import com.ssafy.helphistoryquery.exception.CustomException;
import com.ssafy.helphistoryquery.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class HelpHistoryRepositoryImpl implements HelpHistoryRepository {

    private final ListOperations<String, Object> listOps;

    private final ObjectMapper objectMapper;

    @Override
    public Integer getHelpCount(Long memberId) {
        return Math.toIntExact(Optional.ofNullable(listOps.size("histories:fromMemberId:" + memberId))
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_HELP_HISTORY)));
    }

    @Override
    public List<HelpHistoryEntity> getHelpReceivedList(Long memberId) {
        List<Object> objectList = listOps.getOperations().opsForList().range("histories:fromMemberId:" + memberId, 0, -1);

        List<Object> result = Optional.ofNullable(objectList)
                .orElse(Collections.emptyList());

        return this.getHelpHistoryEntityList(result);

    }

    @Override
    public List<HelpHistoryEntity> getHelpHistoryEntityList(List<Object> objectList) {
        return objectList.stream()
                .map(this::parseJsonToEntity)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public HelpHistoryEntity parseJsonToEntity(Object json) {
        try {
            return objectMapper.readValue(json.toString(), HelpHistoryEntity.class);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.JSON_PARSE_ERROR);
        }
    }
}
