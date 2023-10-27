package com.ssafy.helphistorycommand.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    EXAMPLE(HttpStatus.BAD_REQUEST, "예시");

    private final HttpStatus status;
    private final String message;

}

