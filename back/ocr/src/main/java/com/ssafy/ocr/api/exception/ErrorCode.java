package com.ssafy.ocr.api.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_IMAGE_ERROR(HttpStatus.BAD_REQUEST, "유효하지 않은 이미지입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에서 에러가 발생했습니다.");

    private final HttpStatus status;
    private final String message;

}
