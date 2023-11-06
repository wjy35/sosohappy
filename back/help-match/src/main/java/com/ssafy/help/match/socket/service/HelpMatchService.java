package com.ssafy.help.match.socket.service;

import com.ssafy.help.match.socket.request.HelpMatchRequest;
import com.ssafy.help.match.socket.response.MatchStatusResponse;
import com.ssafy.help.match.socket.response.ReceiveMatchItem;
import org.springframework.data.geo.Point;
import java.util.List;

public interface HelpMatchService {
    MatchStatusResponse viewStatusByMemberId(Long memberId);
    void match(Point point,double metric, Long memberId);
    void saveAndChangeStatus(HelpMatchRequest helpMatchRequest);

    List<ReceiveMatchItem> list(Long memberId);
}
