package com.ssafy.monster.common.exception;

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

    SHORTAGE_OF_CLOVER(HttpStatus.BAD_REQUEST, "보유중인 클로버가 부족합니다."),
    FULL_OF_CLOVER(HttpStatus.BAD_REQUEST, "클로버가 이미 가득 차 있습니다."),
    JSON_PARSE_ERROR(HttpStatus.CONFLICT, "Topic을 변환하는 과정에서 오류가 발생했습니다."),
    MONSTER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 monsterId로 조회된 정보가 없습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 memberId로 조회된 profile이 없습니다."),
    GROWTH_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 memberMonsetId로 조회된 growth 정보가 없습니다."),
    MEMBER_EXIST_ERROR(HttpStatus.CONFLICT, "해당 memberId에 profile 정보가 이미 존재합니다.");

    private final HttpStatus status;
    private final String message;

}