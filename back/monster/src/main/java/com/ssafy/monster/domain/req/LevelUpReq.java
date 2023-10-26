package com.ssafy.monster.domain.req;

import lombok.Getter;

@Getter
public class LevelUpReq {
    private Long memberMonsterId;
    private int clover;
}
