package com.ssafy.helphistoryquery.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.helphistoryquery.api.response.HelpHistoryResponse;

import java.util.List;

public interface HelpHistoryService {
    Integer getHelpCount(Long memberId) throws JsonProcessingException;

    List<HelpHistoryResponse> getHelpReceivedList(Long memberId) throws JsonProcessingException;

}
