package com.ssafy.helphistoryquery.api.controller;

import com.ssafy.helphistoryquery.api.response.FormattedResponse;
import com.ssafy.helphistoryquery.service.impl.HelpHistoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
