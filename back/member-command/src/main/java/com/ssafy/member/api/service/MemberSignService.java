package com.ssafy.member.api.service;

import com.ssafy.member.api.request.SignInRequest;
import com.ssafy.member.api.response.SignInResponse;

public interface MemberSignService {
    SignInResponse signIn(SignInRequest signInRequest);
}
