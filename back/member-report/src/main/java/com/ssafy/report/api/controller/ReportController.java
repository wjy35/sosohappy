package com.ssafy.report.api.controller;

import com.ssafy.report.api.mapper.ReportMapper;
import com.ssafy.report.api.request.ReportRequest;
import com.ssafy.report.api.response.FormattedResponse;
import com.ssafy.report.api.response.ReportedMemberResponse;
import com.ssafy.report.api.service.AdminService;
import com.ssafy.report.api.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/report")
public class ReportController {
    private final AdminService adminService;
    private final ReportService reportService;

    @GetMapping("/")
    ResponseEntity<FormattedResponse> getReportedMember(@RequestHeader Long memberId){
        if(!adminService.isAdmin(memberId)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        FormattedResponse response = FormattedResponse
                .builder()
                .status("success")
                .message("GET REPORTED MEMBER LIST")
                .result("reportedMember",reportService.getReportedMemberList())
                .build();

        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/")
    ResponseEntity<FormattedResponse> report(@RequestBody ReportRequest request){
        reportService.reportMember(ReportMapper.INSTANCE.toEntity(request));

        FormattedResponse response = FormattedResponse
                .builder()
                .status("success")
                .message("SUCCESS REPORT")
                .build();

        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
