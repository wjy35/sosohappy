package com.ssafy.monster.service;

import com.ssafy.monster.domain.res.MonsterRes;

import java.util.List;
import java.util.Map;

public interface MonsterService {

    // 대표 캐릭터 불러오기
    Map<String, Object> searchRepresentativeMonster(Long memberId);

    // 보유중인 캐릭터 불러오기(도감)
    Map<String, Object> searchMonsterList(Long memberId);

    // 클로버 먹이기(레벨업)
    Map<String, Object> updateMonsterClover(Long memberMonsterId, int clover);

    // 회원가입시 초기 지급 캐릭터
    void setInitialMonster(Long memberId);
}
