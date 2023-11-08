package com.ssafy.help.match.socket.controller;

import com.ssafy.help.match.api.response.FormattedResponse;
import com.ssafy.help.match.socket.exception.UnAcceptableException;
import com.ssafy.help.match.socket.request.HelpAcceptRequest;
import com.ssafy.help.match.socket.request.HelpArrivalRequest;
import com.ssafy.help.match.socket.request.HelpCancelRequest;
import com.ssafy.help.match.socket.request.HelpCompleteRequest;
import com.ssafy.help.match.socket.service.HelpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelpController {
    private final HelpService helpService;

    @PostMapping("/accept")
    ResponseEntity<?> accept(@RequestBody HelpAcceptRequest helpAcceptRequest) {
        try{
            helpService.accept(helpAcceptRequest);
        }catch (UnAcceptableException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/arrival")
    ResponseEntity<?> arrival(@RequestBody HelpArrivalRequest helpArrivalRequest){
        FormattedResponse response = null;

        try{
            helpService.arrival(helpArrivalRequest.getMemberId());
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
            helpService.complete(helpCompleteRequest.getMemberId());
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

    @PostMapping("/cancel/help")
    ResponseEntity<?> cancel(@RequestBody HelpCancelRequest helpCancelRequest){
        FormattedResponse response;

        try{
            helpService.cancel(helpCancelRequest.getMemberId());
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
