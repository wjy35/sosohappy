package com.ssafy.member.api.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignInResponse {
    String accessToken;
    String refreshToken;
}
