package com.ssafy.helphistoryquery.service.impl;

import com.ssafy.helphistoryquery.api.mapper.HelpHistoryMapper;
import com.ssafy.helphistoryquery.api.response.HelpHistoryResponse;
import com.ssafy.helphistoryquery.db.entity.HelpHistoryEntity;
import com.ssafy.helphistoryquery.db.repository.CategoryRepository;
import com.ssafy.helphistoryquery.db.repository.HelpHistoryRepository;
import com.ssafy.helphistoryquery.service.HelpHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HelpHistoryServiceImpl implements HelpHistoryService {

    private final HelpHistoryMapper helpHistoryMapper;

    private final HelpHistoryRepository helpHistoryRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public Integer getHelpCount(Long memberId){

       return helpHistoryRepository.getHelpCount(memberId);
    }

    @Override
    public List<HelpHistoryResponse> getHelpReceivedList(Long memberId){
        List<HelpHistoryEntity> helpHistoryEntityList = helpHistoryRepository.getHelpReceivedList(memberId);

        List<HelpHistoryResponse> helpHistoryResponseList = new ArrayList<>();

        for (HelpHistoryEntity helpHistoryEntity : helpHistoryEntityList) {
            Optional.ofNullable(categoryRepository.getCategoryInfo(helpHistoryEntity.getCategoryId()))
                    .ifPresent(categoryEntity ->
                            helpHistoryResponseList.add(helpHistoryMapper.entityToResponse(categoryEntity, helpHistoryEntity)));

        }

        return helpHistoryResponseList;
    }

}
