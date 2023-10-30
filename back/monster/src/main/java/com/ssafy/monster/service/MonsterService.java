package com.ssafy.monster.service;

import com.ssafy.monster.domain.res.CloverRes;
import com.ssafy.monster.domain.res.MonsterRes;

import java.util.List;
import java.util.Map;

public interface MonsterService {

    // 대표 캐릭터 불러오기
    MonsterRes searchRepresentativeMonster(Long memberId);

    // 보유중인 캐릭터 불러오기(도감)
    List<MonsterRes> searchMonsterList(Long memberId);

    // 클로버 정보 조회
    CloverRes searchCloverInfo(Long memberId);

    // 클로버 먹이기(레벨업)
    MonsterRes updateMonsterClover(Long memberMonsterId, int clover);

    // 회원가입시 초기 지급 캐릭터
    void setInitialMonster(Long memberId);

    //도움 완료 시 나눔이 모음이 클로버 증가
    void updateClover(Long fromMemberId, Long toMemberId, int clover);

    void updateMemberMonsterProfile(Long memberId, int profileMonsterId);
}
