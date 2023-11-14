package com.ssafy.helphistoryquery.api.controller;

import com.ssafy.helphistoryquery.api.response.FormattedResponse;
import com.ssafy.helphistoryquery.api.response.HelpCertificateResponse;
import com.ssafy.helphistoryquery.api.response.HelpHistoryResponse;
import com.ssafy.helphistoryquery.service.impl.HelpHistoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HelpHistoryController {

    private final HelpHistoryServiceImpl helpHistoryService;

    @GetMapping("/count")
    public ResponseEntity<?> getHelpCount (@RequestHeader("memberId") long memberId) {
        int count = helpHistoryService.getHelpCount(memberId);
        FormattedResponse response = FormattedResponse.builder()
                .status("success")
                .message("GET HELP COUNT")
                .result("memberId",memberId)
                .result("count",count)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getHelpReceivedList (@RequestHeader("memberId") long memberId) {
        List<HelpHistoryResponse> helpHistoryResponseList = helpHistoryService.getHelpReceivedList(memberId);
        FormattedResponse response = FormattedResponse.builder()
                .status("success")
                .message("GET Help Given List")
                .result("historyList",helpHistoryResponseList)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/certificate")
    public ResponseEntity<?> getCertificate (@RequestHeader("memberId") long memberId){

        List<HelpCertificateResponse> helpCertificateResponseList = helpHistoryService.getHelpCertificate(memberId);

        FormattedResponse response = FormattedResponse.builder()
                .status("success")
                .message("GET Help Given List")
                .result("helpCertificateResponseList", helpCertificateResponseList)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
