package com.ssafy.notification.api.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class CustomException extends RuntimeException {

    private ErrorCode errorCode;
    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.setStackTrace(new StackTraceElement[0]);
        this.errorCode = errorCode;
    }

}
