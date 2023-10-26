package com.ssafy.member.api.request;

import lombok.Data;

@Data
public class SignInRequest {
    String id;
    String password;
}
