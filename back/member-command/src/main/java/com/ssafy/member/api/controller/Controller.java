package com.ssafy.member.api.controller;

import com.ssafy.member.api.mapper.MemberMapper;
import com.ssafy.member.api.request.MemberModifyRequest;
import com.ssafy.member.api.request.MemberSignUpRequest;
import com.ssafy.member.api.request.SignInRequest;
import com.ssafy.member.api.response.FormattedResponse;
import com.ssafy.member.api.service.MemberManageService;
import com.ssafy.member.api.service.MemberSignService;
import com.ssafy.member.util.AuthTokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final MemberSignService memberSignService;
    private final MemberManageService memberManageService;

    @PostMapping("/sign-in")
    ResponseEntity<FormattedResponse> singIn(@RequestBody SignInRequest signInRequest){
        AuthTokenDTO authTokenDTO = memberSignService.signIn(signInRequest);

        FormattedResponse formattedResponse = FormattedResponse
                .builder()
                .status("success")
                .message("Sign In Success")
                .result("authorization",authTokenDTO)
                .build();

        return new ResponseEntity<>(formattedResponse, HttpStatus.OK);
    }

    @PostMapping("/")
    ResponseEntity<FormattedResponse> signUp(@RequestBody MemberSignUpRequest memberSignUpRequest){
        memberSignService.singUp(MemberMapper.INSTANCE.toEntity(memberSignUpRequest));

        FormattedResponse formattedResponse = FormattedResponse
                .builder()
                .status("success")
                .message("Sign Up Success")
                .build();

        return new ResponseEntity<>(formattedResponse, HttpStatus.OK);
    }

    @PatchMapping("/")
    ResponseEntity<FormattedResponse> modify(@RequestHeader Long memberId, @RequestBody MemberModifyRequest memberModifyRequest){
        memberManageService.modify(MemberMapper.INSTANCE.toEntity(memberId,memberModifyRequest));

        FormattedResponse formattedResponse = FormattedResponse
                .builder()
                .status("success")
                .message("Sign Up Success")
                .build();

        return new ResponseEntity<>(formattedResponse, HttpStatus.OK);
    }

}
