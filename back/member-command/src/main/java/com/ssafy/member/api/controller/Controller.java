package com.ssafy.member.api.controller;

import com.ssafy.member.api.request.SignInRequest;
import com.ssafy.member.api.response.FormattedResponse;
import com.ssafy.member.api.service.MemberSignService;
import com.ssafy.member.util.AuthTokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {
    private final MemberSignService memberSignService;

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
}
