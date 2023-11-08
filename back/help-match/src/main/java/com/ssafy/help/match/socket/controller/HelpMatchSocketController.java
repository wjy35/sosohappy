package com.ssafy.help.match.socket.controller;

import com.ssafy.help.match.socket.request.*;
import com.ssafy.help.match.socket.response.MatchStatusResponse;
import com.ssafy.help.match.socket.response.PushMatchListResponse;
import com.ssafy.help.match.socket.service.HelpService;
import com.ssafy.help.match.util.ObjectSerializer;
import com.ssafy.help.match.socket.service.HelpMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HelpMatchSocketController{
    private final ObjectSerializer objectSerializer;
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final HelpMatchService helpMatchService;

    @SubscribeMapping("/topic/match/status/{memberId}")
    void status(@DestinationVariable Long memberId){
        MatchStatusResponse response = helpMatchService.getStatus(memberId);
        simpMessageSendingOperations.convertAndSend("/topic/match/status/"+memberId, objectSerializer.serialize(response));
    }

    @SubscribeMapping("/topic/match/list/{memberId}")
    void list(@DestinationVariable Long memberId){
        PushMatchListResponse response = PushMatchListResponse
                .builder()
                .receiveMatchList(helpMatchService.list(memberId))
                .build();

        simpMessageSendingOperations.convertAndSend("/topic/match/list/"+memberId, objectSerializer.serialize(response));
    }

    @MessageMapping("/match")
    void match(@Payload HelpMatchRequest helpMatchRequest){
        helpMatchService.match(helpMatchRequest);
    }

    @PostMapping("/cancel/match")
    void cancel(Long memberId){
        helpMatchService.cancel(memberId);
    }
}
