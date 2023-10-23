package com.ssafy.monster.domain.res;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MonsterRes {

    private int memberMonsterId;
    private Byte typeId;
    private String typeName;
    private int level;         // 대표캐릭터(프로필)의 레벨(보여지는 사진)
    private Double currentPoint;  // 현재 경험치 (퍼센트) (실 레벨 기준)

}
