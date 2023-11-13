package com.ssafy.ocr.api.exception;

import com.ssafy.ocr.api.response.FormattedResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<FormattedResponse> onCustomException(CustomException e) {

        FormattedResponse formattedResponse = FormattedResponse.builder()
                .status("fail")
                .message(e.getErrorCode().getMessage())
                .build();

        return new ResponseEntity<>(formattedResponse, e.getErrorCode().getStatus());
    }
}
