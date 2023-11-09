package com.ssafy.help.match.socket.controller;

import com.ssafy.help.match.api.request.PointSaveRequest;
import com.ssafy.help.match.api.response.FormattedResponse;
import com.ssafy.help.match.api.service.MemberPointManageService;
import com.ssafy.help.match.db.entity.HelpMatchStatus;
import com.ssafy.help.match.socket.request.*;
import com.ssafy.help.match.socket.response.MatchStatusResponse;
import com.ssafy.help.match.socket.response.PushMatchListResponse;
import com.ssafy.help.match.socket.service.HelpService;
import com.ssafy.help.match.util.ObjectSerializer;
import com.ssafy.help.match.socket.service.HelpMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
@RequiredArgsConstructor
public class HelpMatchSocketController{
    private final ObjectSerializer objectSerializer;
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final HelpMatchService helpMatchService;
    private final MemberPointManageService memberPointManageService;

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
    ResponseEntity<FormattedResponse> cancel(@RequestBody HelpMatchCancelRequest helpMatchCancelRequest ){

        FormattedResponse response;
        try{
            helpMatchService.cancel(helpMatchCancelRequest.getMemberId());
            response = FormattedResponse
                    .builder()
                    .status("success")
                    .message("SUCCESS CANCEL")
                    .build();
        }catch (Exception e){
            response = FormattedResponse
                    .builder()
                    .status("fail")
                    .message("FAIL CANCEL")
                    .build();
            return new ResponseEntity<>(response,HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @MessageMapping("/point")
    void save(@Payload PointSaveRequest pointSaveRequest){
        memberPointManageService.save(
                new Point(pointSaveRequest.getLongitude(), pointSaveRequest.getLatitude()),
                pointSaveRequest.getMemberId(),
                pointSaveRequest.getOtherMemberId()
        );
    }

    @GetMapping("/match/status")
    ResponseEntity<FormattedResponse> simpStatus(@RequestHeader Long memberId){
        HelpMatchStatus helpMatchStatus = helpMatchService.getSimpStatus(memberId);

        FormattedResponse formattedResponse = FormattedResponse
                .builder()
                .status("success")
                .message("SUCCESS GET STATUS")
                .result("helpMatchStatus",helpMatchStatus)
                .build();

        return new ResponseEntity<>(formattedResponse,HttpStatus.OK);
    }
}
