package com.ssafy.helphistoryquery.service;

import com.ssafy.helphistoryquery.api.request.HelpHistoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HelpHistoryServiceImpl implements HelpHistoryService {

    private final ListOperations<String, HelpHistoryRequest> listOps;

    @Override
    public Integer getHelpCount(Long memberId) {
        return Math.toIntExact(Optional.ofNullable(listOps.size("histories:toMemberId:" + memberId))
                .orElseThrow(RuntimeException::new));
    }

}
