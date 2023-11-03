package com.ssafy.help.match.db.entity;

public enum HelpMatchStatus {
    DEFAULT,
    ON_MOVE, // 매칭 완료후 이동중
    ON_MATCH_PROGRESS, // 매칭중
    WAIT_COMPLETE // 도움을 받은 뒤 대기중
}
