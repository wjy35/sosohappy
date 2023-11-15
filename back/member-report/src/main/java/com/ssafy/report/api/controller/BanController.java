package com.ssafy.report.api.controller;

import com.ssafy.report.api.request.BanRequest;
import com.ssafy.report.api.response.FormattedResponse;
import com.ssafy.report.api.service.AdminService;
import com.ssafy.report.api.service.BanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ban")
public class BanController {
    private final BanService banService;
    private final AdminService adminService;

    @PostMapping("/")
    ResponseEntity<FormattedResponse> ban(@RequestHeader Long memberId, @RequestBody BanRequest banRequest){
        if(!adminService.isAdmin(memberId)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        banService.ban(banRequest.getBannedMemberId(), banRequest.getDay());

        FormattedResponse response = FormattedResponse
                .builder()
                .status("success")
                .message("SUCCESS BAN")
                .build();

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/")
    ResponseEntity<FormattedResponse> list(@RequestHeader Long memberId){
        if(!adminService.isAdmin(memberId)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        FormattedResponse response = FormattedResponse
                .builder()
                .status("success")
                .message("SUCCESS GET LIST")
                .result("bannedMemberList",banService.getBannedMemberList())
                .build();

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/{cancelMemberId}")
    ResponseEntity<FormattedResponse> cancel(@RequestHeader Long memberId,@PathVariable Long cancelMemberId){
        if(!adminService.isAdmin(memberId)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        banService.cancel(cancelMemberId);
        FormattedResponse response = FormattedResponse
                .builder()
                .status("success")
                .message("SUCCESS GET CANCEL")
                .build();

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
