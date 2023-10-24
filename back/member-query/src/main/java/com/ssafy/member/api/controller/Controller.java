package com.ssafy.member.api.controller;

import com.ssafy.member.api.mapper.MemberMapper;
import com.ssafy.member.api.response.FormattedResponse;
import com.ssafy.member.api.response.MemberInformationResponse;
import com.ssafy.member.api.service.MemberInformationService;
import com.ssafy.member.db.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final MemberInformationService memberInformationService;

    @GetMapping("/")
    ResponseEntity<FormattedResponse> getMemberInformation(@RequestHeader Long memberId){
        MemberEntity memberEntity
                = Optional.of(memberInformationService.getInformationByMemberId(memberId)).get();

        MemberInformationResponse memberInformationResponse
                = MemberMapper.INSTANCE.entityToResponse(memberEntity);


        FormattedResponse formattedResponse = FormattedResponse
                .builder()
                .status("success")
                .message("GET MEMBER INFORMATION OK")
                .result("member",memberInformationResponse)
                .build();

        return new ResponseEntity<>(formattedResponse,HttpStatus.OK);
    }

}
