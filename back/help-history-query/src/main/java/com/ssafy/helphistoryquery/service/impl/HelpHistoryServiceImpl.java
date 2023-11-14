package com.ssafy.helphistoryquery.service.impl;

import com.ssafy.helphistoryquery.api.mapper.HelpHistoryMapper;
import com.ssafy.helphistoryquery.api.response.HelpCertificateResponse;
import com.ssafy.helphistoryquery.api.response.HelpHistoryResponse;
import com.ssafy.helphistoryquery.db.entity.HelpCertificateEntity;
import com.ssafy.helphistoryquery.db.entity.HelpHistoryEntity;
import com.ssafy.helphistoryquery.db.repository.HelpHistoryRepository;
import com.ssafy.helphistoryquery.service.HelpHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HelpHistoryServiceImpl implements HelpHistoryService {

    private final HelpHistoryMapper helpHistoryMapper;

    private final HelpHistoryRepository helpHistoryRepository;

    @Override
    public Integer getHelpCount(Long memberId){

       return helpHistoryRepository.getHelpCount(memberId);
    }

    @Override
    public List<HelpHistoryResponse> getHelpReceivedList(Long memberId){
        List<HelpHistoryEntity> helpHistoryEntityList = helpHistoryRepository.getHelpReceivedList(memberId);

        List<HelpHistoryResponse> helpHistoryResponseList = new ArrayList<>();

        for (HelpHistoryEntity helpHistoryEntity : helpHistoryEntityList) {
            helpHistoryResponseList.add(helpHistoryMapper.entityToResponse(helpHistoryEntity));
        }

        return helpHistoryResponseList;
    }

    @Override
    public List<HelpCertificateResponse> getHelpCertificate(Long memberId) {
        List<HelpCertificateEntity> helpCertificateEntities = helpHistoryRepository.getHelpCertificateList(memberId);

        List<HelpCertificateResponse> helpCertificateResponses = new ArrayList<>();

        for(HelpCertificateEntity helpCertificateEntity : helpCertificateEntities){
            helpCertificateResponses.add(helpHistoryMapper.entityToResponse(helpCertificateEntity));
        }
        return helpCertificateResponses;
    }

}
