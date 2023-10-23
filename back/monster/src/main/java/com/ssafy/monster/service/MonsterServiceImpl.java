package com.ssafy.monster.service;

import com.ssafy.monster.domain.entity.MemberMonsterProfile;
import com.ssafy.monster.domain.entity.MonsterType;
import com.ssafy.monster.domain.res.CloverRes;
import com.ssafy.monster.domain.res.MonsterRes;
import com.ssafy.monster.repository.GrowthRepository;
import com.ssafy.monster.repository.MonsterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class MonsterServiceImpl implements MonsterService{

    private final MonsterRepository monsterRepository;
    private final GrowthRepository growthRepository;

    @Autowired
    public MonsterServiceImpl(MonsterRepository monsterRepository, GrowthRepository growthRepository) {
        this.monsterRepository = monsterRepository;
        this.growthRepository = growthRepository;
    }


    /**
     * 대표 캐릭터 불러오기
    */
    @Override
    public Map<String, Object> searchRepresentativeMonster(Long memberId) {

        MemberMonsterProfile profile = monsterRepository.findByMemberId(memberId).get();
        MonsterType type = profile.getMonsterInfo().getMonsterType();

        int requiredClover = profile.getMonsterInfo().getRequiredClover();
        Double prevRequiredClover = monsterRepository.getPrevRequiredClover(profile.getMonsterInfo().getMonsterLevel());
        int currentClover = growthRepository.findByMemberMonsterProfile_MemberIdAndMonsterType_TypeId(profile.getMemberId(), type.getTypeId()).get().getMonsterClover();

        CloverRes clover = CloverRes.builder()
                .memberClover(profile.getMemberClover())
                .memberAccruedClover(profile.getMemberAccruedClover())
                .build();

        MonsterRes monster = MonsterRes.builder()
                .memberMonsterId(profile.getMonsterInfo().getMonsterId())
                .typeId(type.getTypeId())
                .typeName(type.getTypeName())
                .level(profile.getMonsterInfo().getMonsterLevel())
                .currentPoint((currentClover-prevRequiredClover) / requiredClover)
                .build();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("clover", clover);
        resultMap.put("monster", monster);

        return resultMap;
    }

}
