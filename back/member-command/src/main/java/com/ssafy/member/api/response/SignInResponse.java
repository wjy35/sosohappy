package com.ssafy.member.api.response;

import lombok.Data;

@Data
public class SignInResponse {
    String accessToken;
    String refreshToken;
}
