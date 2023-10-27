package com.ssafy.helphistoryquery.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    NOT_FOUND_HELP_HISTORY(HttpStatus.BAD_REQUEST, "해당 유저의 기록이 없습니다."),

    JSON_PARSE_ERROR(HttpStatus.BAD_REQUEST, "JSON을 파싱할 수 없습니다."),
    NOT_FOUND_CATEGORY(HttpStatus.BAD_REQUEST, "없는 카테고리 번호입니다."),
    NOT_FOUND_HISTORY(HttpStatus.BAD_REQUEST, "없는 기록입니다.");


    private final HttpStatus status;
    private final String message;

}
