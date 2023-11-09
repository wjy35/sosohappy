package com.ssafy.help.match.socket.service;

import com.ssafy.help.match.socket.request.HelpAcceptRequest;

public interface HelpService {
    void accept(HelpAcceptRequest helpAcceptRequest);
    void arrival(Long memberId);
    void complete(Long memberId);
    void cancel(Long memberId);
}
