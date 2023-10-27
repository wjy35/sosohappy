package com.ssafy.member.api.service;

import com.ssafy.member.api.request.SignInRequest;
import com.ssafy.member.api.response.SignInResponse;
import com.ssafy.member.util.AuthTokenDTO;

public interface MemberSignService {
    AuthTokenDTO signIn(SignInRequest signInRequest);
}
