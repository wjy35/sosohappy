package com.ssafy.ocr.api.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_DOCUMENT_NUMBER(HttpStatus.BAD_REQUEST, "문서번호를 확인하지 못했습니다."),
    INVALID_DOCUMENT_ERROR(HttpStatus.BAD_REQUEST, "장애인 증명서가 아닙니다."),
    INVALID_IMAGE_ERROR(HttpStatus.BAD_REQUEST, "유효하지 않은 이미지입니다."),
    CRAWLING_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "크롤링 과정에서 에러가 발생했습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에서 에러가 발생했습니다.");

    private final HttpStatus status;
    private final String message;

}
