package com.ssafy.ocr.api.exception;

import com.ssafy.ocr.api.response.FormattedResponse;
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
