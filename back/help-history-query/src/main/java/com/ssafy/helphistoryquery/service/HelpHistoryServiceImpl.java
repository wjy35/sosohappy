package com.ssafy.helphistoryquery.service;

import com.ssafy.helphistoryquery.api.request.HelpHistoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HelpHistoryServiceImpl implements HelpHistoryService {

    private final ListOperations<String, HelpHistoryRequest> listOps;

    @Override
    public int getHelpCount(long memberId) {
        if(listOps.size("histories:toMemberId:" + memberId) == null) throw new RuntimeException();
        return Math.toIntExact(listOps.size("histories:toMemberId:" + memberId));
    }

}
