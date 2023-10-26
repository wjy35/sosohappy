package com.ssafy.helphistoryquery.db.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.helphistoryquery.db.entity.HelpHistoryEntity;

import java.util.List;

public interface HelpHistoryRepository {
    Integer getHelpCount(Long memberId) throws JsonProcessingException;

    List<HelpHistoryEntity> getHelpReceivedList(Long memberId) throws JsonProcessingException;

    List<HelpHistoryEntity> getHelpHistoryEntityList(List<Object> objectList);

    HelpHistoryEntity parseJsonToEntity(Object json);
}
