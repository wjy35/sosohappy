package com.ssafy.monster.domain.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MonsterRes {

    private Long memberMonsterId;
    private Byte typeId;
    private String typeName;
    private LevelInfo levelInfo;
}
