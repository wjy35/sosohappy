package com.ssafy.category.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /**
     * 비즈니스 에러 (확인 가능한 예외 상황)
     */
    /* 400 BAD_REQUEST : 잘못된 요청 */
    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    /* 404 NOT_FOUND : Resource를 찾을 수 없음 */
    /* 409 : CONFLICT : Resource의 현재 상태와 충돌. 보통 중복된 데이터 존재 */

    NOT_FOUND(HttpStatus.NOT_FOUND, "categoryId가 존재하지 않습니다."),
    JSON_PARSE_ERROR(HttpStatus.CONFLICT, "Topic을 변환하는 과정에서 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String message;

}