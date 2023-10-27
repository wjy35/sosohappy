package com.ssafy.monster.common.exception;

import com.ssafy.monster.common.response.FormattedResponse;
import com.thoughtworks.xstream.core.BaseException;
import lombok.extern.slf4j.Slf4j;
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
