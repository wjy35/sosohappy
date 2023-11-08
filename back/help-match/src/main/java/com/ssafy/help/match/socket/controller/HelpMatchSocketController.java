package com.ssafy.help.match.socket.controller;

import com.ssafy.help.match.api.response.FormattedResponse;
import com.ssafy.help.match.socket.exception.UnAcceptableException;
import com.ssafy.help.match.socket.request.*;
import com.ssafy.help.match.socket.response.MatchStatusResponse;
import com.ssafy.help.match.socket.response.PushMatchListResponse;
import com.ssafy.help.match.util.ObjectSerializer;
import com.ssafy.help.match.socket.service.HelpMatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @PostMapping("/accept")
    ResponseEntity<?> accept(@RequestBody HelpAcceptRequest helpAcceptRequest) {
        try{
            helpMatchService.accept(helpAcceptRequest);
        }catch (UnAcceptableException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/arrival")
    ResponseEntity<?> arrival(@RequestBody HelpArrivalRequest helpArrivalRequest){
        FormattedResponse response = null;

        try{
            helpMatchService.arrival(helpArrivalRequest.getMemberId());
            response = FormattedResponse
                    .builder()
                    .status("success")
                    .message("SUCCESS ARRIVAL")
                    .build();

        }catch (Exception e){
            response = FormattedResponse
                    .builder()
                    .status("fail")
                    .message("FAIL ARRIVAL")
                    .build();
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/complete")
    ResponseEntity<?> complete(@RequestBody HelpCompleteRequest helpCompleteRequest){
        FormattedResponse response = null;

        try{
            helpMatchService.complete(helpCompleteRequest.getMemberId());
            response = FormattedResponse
                    .builder()
                    .status("success")
                    .message("SUCCESS COMPLETE")
                    .build();
        }catch (Exception e){
            response = FormattedResponse
                    .builder()
                    .status("fail")
                    .message("FAIL COMPLETE")
                    .build();
            return new ResponseEntity<>(response,HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/complete")
    ResponseEntity<?> cancel(@RequestBody HelpCancelRequest helpCancelRequest){
        FormattedResponse response;

        try{
            helpMatchService.cancel(helpCancelRequest.getMemberId());
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

}
