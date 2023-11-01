package com.ssafy.helphistorysync.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    CLASS_CAST_ERROR(HttpStatus.BAD_REQUEST, "직렬화에 실패했습니다.");

    private final HttpStatus status;
    private final String message;

}
