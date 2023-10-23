package com.ssafy.monster.service;

import com.ssafy.monster.domain.res.MonsterRes;

import java.util.List;
import java.util.Map;

public interface MonsterService {

    // 대표 캐릭터 불러오기
    Map<String, Object> searchRepresentativeMonster(Long memberId);

    // 보유중인 캐릭터 불러오기(도감)
    Map<String, Object> searchMonsterList(Long memberId);

    Map<String, Object> updateMonsterClover(Long memberMonsterId, int clover);
}
