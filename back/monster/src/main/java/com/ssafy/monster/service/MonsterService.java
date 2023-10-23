package com.ssafy.monster.service;

import com.ssafy.monster.domain.res.MonsterRes;

import java.util.Map;

public interface MonsterService {
    Map<String, Object> searchRepresentativeMonster(Long memberId);
}
