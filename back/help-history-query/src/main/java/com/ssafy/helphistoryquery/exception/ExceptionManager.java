package com.ssafy.helphistoryquery.exception;

import com.ssafy.helphistoryquery.api.response.FormattedResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(CustomException.class)
    public FormattedResponse onCustomException(CustomException e) {

        return FormattedResponse.builder()
                .status("fail")
                .message(e.getErrorCode().getMessage())
                .build();
    }
}
