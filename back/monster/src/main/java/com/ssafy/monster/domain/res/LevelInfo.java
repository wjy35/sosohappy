package com.ssafy.monster.domain.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class LevelInfo {

    int currentLevel;
    int requiredClover;
    int currentClover;
    Double currentCloverRate;

}
