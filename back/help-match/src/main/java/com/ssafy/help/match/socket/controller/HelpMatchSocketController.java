package com.ssafy.help.match.socket.controller;

import com.ssafy.help.match.socket.request.HelpMatchRequest;
import com.ssafy.help.match.socket.response.MatchStatusResponse;
import com.ssafy.help.match.socket.response.ReceiveMatchListResponse;
import com.ssafy.help.match.socket.response.ReceiveMatchType;
import com.ssafy.help.match.util.ObjectSerializer;
import com.ssafy.help.match.socket.service.HelpMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class HelpMatchSocketController{
    private final ObjectSerializer objectSerializer;
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final HelpMatchService helpMatchService;
    private final double[] metricList = {500d,1000d,1500d};

    @SubscribeMapping("/topic/match/status/{memberId}")
    void status(@DestinationVariable Long memberId){
        MatchStatusResponse response = helpMatchService.viewStatusByMemberId(memberId);
        simpMessageSendingOperations.convertAndSend("/topic/match/status/"+memberId, objectSerializer.serialize(response));
    }

    @SubscribeMapping("/topic/match/list/{memberId}")
    void list(@DestinationVariable Long memberId){
        ReceiveMatchListResponse response = ReceiveMatchListResponse
                .builder()
                .receiveMatchType(ReceiveMatchType.PUSH)
                .receiveMatchList(helpMatchService.list(memberId))
                .build();

        simpMessageSendingOperations.convertAndSend("/topic/match/list/"+memberId, objectSerializer.serialize(response));
    }

    @MessageMapping("/match")
    void match(@Payload HelpMatchRequest helpMatchRequest){
        MatchStatusResponse response = helpMatchService.saveAndGetMatchStatus(helpMatchRequest);
        simpMessageSendingOperations.convertAndSend("/topic/match/status/"+helpMatchRequest.getMemberId(), objectSerializer.serialize(response));

        for(double metric: metricList){
            helpMatchService.match(new Point(helpMatchRequest.getLongitude(),helpMatchRequest.getLatitude()),metric, helpMatchRequest.getMemberId());
        }
    }
}
