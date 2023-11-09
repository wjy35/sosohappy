package com.ssafy.help.match.socket.service;

import com.ssafy.help.match.db.entity.HelpMatchStatus;
import com.ssafy.help.match.socket.request.HelpMatchRequest;
import com.ssafy.help.match.socket.response.MatchStatusResponse;
import com.ssafy.help.match.socket.response.PushMatchItem;
import java.util.List;

public interface HelpMatchService {
    MatchStatusResponse getStatus(Long memberId);
    HelpMatchStatus getSimpStatus(Long memberId);
    void match(HelpMatchRequest helpMatchRequest);
    List<PushMatchItem> list(Long memberId);
    void cancel(Long memberId);
}
