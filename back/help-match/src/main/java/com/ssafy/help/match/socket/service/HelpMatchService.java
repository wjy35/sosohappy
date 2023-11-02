package com.ssafy.help.match.socket.service;

import com.ssafy.help.match.socket.response.MatchStatusResponse;

public interface HelpMatchService {
    MatchStatusResponse viewStatusByMemberId(Long memberId);
}
