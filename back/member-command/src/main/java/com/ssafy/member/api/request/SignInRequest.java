package com.ssafy.member.api.request;

import lombok.Data;

@Data
public class SignInRequest {
    String inputId;
    String inputPassword;
}
