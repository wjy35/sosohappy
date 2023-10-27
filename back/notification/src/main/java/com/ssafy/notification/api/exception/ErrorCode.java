package com.ssafy.notification.api.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    MALFORMED_DEVICE_TOKEN(HttpStatus.BAD_REQUEST, "YOUR TOKEN IS MALFORMED!");

    private final HttpStatus status;
    private final String message;
}
