package com.ssafy.helphistoryquery.service.impl;

import com.ssafy.helphistoryquery.db.repository.impl.HelpHistoryRepositoryImpl;
import com.ssafy.helphistoryquery.service.HelpHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelpHistoryServiceImpl implements HelpHistoryService {

    private final HelpHistoryRepositoryImpl helpHistoryRepository;

    @Override
    public Integer getHelpCount(Long memberId) {
       return helpHistoryRepository.getHelpCount(memberId);
    }

}
