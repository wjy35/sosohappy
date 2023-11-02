package com.ssafy.help.match.api.controller;

import com.ssafy.help.match.api.request.PointSaveRequest;
import com.ssafy.help.match.api.response.FormattedResponse;
import com.ssafy.help.match.api.service.MemberPointManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
@RequiredArgsConstructor
public class HelpMatchController {
    private final MemberPointManageService memberPointManageService;

    @PostMapping("/point")
    ResponseEntity<FormattedResponse> save(@RequestHeader Long memberId, @RequestBody PointSaveRequest pointSaveRequest){
        memberPointManageService.save(new Point(pointSaveRequest.getLongitude(), pointSaveRequest.getLatitude()),memberId);

        FormattedResponse response = FormattedResponse
                .builder()
                .status("success")
                .result("result",true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
