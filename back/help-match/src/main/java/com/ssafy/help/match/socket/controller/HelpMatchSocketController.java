package com.ssafy.help.match.socket.controller;

import com.ssafy.help.match.socket.response.MatchStatusResponse;
import com.ssafy.help.match.util.ObjectSerializer;
import com.ssafy.help.match.socket.service.HelpMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class HelpMatchSocketController{
    private final ObjectSerializer objectSerializer;
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final HelpMatchService helpMatchService;

    @SubscribeMapping("/topic/match/status/{memberId}")
    void match(@DestinationVariable Long memberId){
        MatchStatusResponse response = helpMatchService.viewStatusByMemberId(memberId);
        simpMessageSendingOperations.convertAndSend("/topic/match/status/"+memberId, objectSerializer.serialize(response));
    }

}
