package com.ssafy.help.match.db.entity;

public enum HelpMatchStatus {
    Default(111),
    OnMove(222),
    WaitComplete(333);

    private final int status;

    HelpMatchStatus(Integer status) {
        this.status = status;
    }
}
