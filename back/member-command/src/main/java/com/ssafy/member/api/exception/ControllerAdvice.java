package com.ssafy.member.api.exception;

import com.ssafy.member.api.response.FormattedResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(CustomException.class)
    public FormattedResponse handleCustomException(CustomException e) {
        return FormattedResponse
                .builder()
                .status("fail")
                .message(e.getErrorCode().getMessage())
                .build();
    }

}
