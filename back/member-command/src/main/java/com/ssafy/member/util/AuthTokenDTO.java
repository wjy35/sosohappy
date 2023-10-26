package com.ssafy.member.util;

import lombok.*;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthTokenDTO {
    String accessToken;
    String refreshToken;
}
