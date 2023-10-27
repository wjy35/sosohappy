package com.ssafy.member.api.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    WRONG_ID(HttpStatus.BAD_REQUEST, "Input Wrong ID"),
    WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "Input Wrong Password");

    private final HttpStatus status;
    private final String message;
}