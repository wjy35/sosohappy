package com.ssafy.member.api.exception;

import com.ssafy.member.api.response.FormattedResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(CustomException.class)
    public FormattedResponse onCustomException(CustomException customException) {
        log.debug("{}",customException.getMessage());

        return FormattedResponse.builder()
                .status("fail")
                .message(customException.getErrorCode().getMessage())
                .build();
    }


}
