package com.ssafy.monster.service;

import com.netflix.discovery.shared.Pair;
import com.ssafy.monster.common.exception.CustomException;
import com.ssafy.monster.common.exception.ErrorCode;
import com.ssafy.monster.domain.entity.MemberMonsterGrowth;
import com.ssafy.monster.domain.entity.MemberMonsterProfile;
import com.ssafy.monster.domain.entity.MonsterInfo;
import com.ssafy.monster.domain.entity.MonsterType;
import com.ssafy.monster.domain.res.CloverRes;
import com.ssafy.monster.domain.res.MonsterRes;
import com.ssafy.monster.mapper.CloverMapper;
import com.ssafy.monster.mapper.MonsterMapper;
import com.ssafy.monster.repository.GrowthRepository;
import com.ssafy.monster.repository.InfoRepository;
import com.ssafy.monster.repository.ProfileRepository;
import com.ssafy.monster.repository.TypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class MonsterServiceImpl implements MonsterService{

    private final ProfileRepository profileRepository;
    private final GrowthRepository growthRepository;
    private final InfoRepository infoRepository;
    private final TypeRepository typeRepository;

    @Autowired
    public MonsterServiceImpl(ProfileRepository profileRepository, GrowthRepository growthRepository, InfoRepository infoRepository, TypeRepository typeRepository) {
        this.profileRepository = profileRepository;
        this.growthRepository = growthRepository;
        this.infoRepository = infoRepository;
        this.typeRepository = typeRepository;
    }

    /**
     * 경험치 구간 가져오기
     */
    private double[] expArr; //누적 경험치

    @PostConstruct
    private void getExpinfo() {
        List<Integer> expList = infoRepository.getMonsterCloverInfo();
        expArr = expList.stream().mapToDouble(i -> i).toArray();
    }

    private Pair<Integer, Double> getCurrentPoint(int currentClover){
        int startIdx = 0;
        int midIdx = 0;
        int endIdx = expArr.length -1;

        while(startIdx <= endIdx) {
            midIdx = (startIdx+endIdx) / 2;
            if(expArr[midIdx] == currentClover) {
                break;
            } else if(expArr[midIdx] > currentClover) {
                endIdx = midIdx-1;
            } else {
                startIdx = midIdx+1;
            }
        }

        int currentLevel = (startIdx+endIdx) / 2 + 1;
        if(currentLevel == 0) {
            currentLevel += 1;
        }
        int requiredClover = (int) (expArr[currentLevel] - expArr[currentLevel-1]);
        Double prevRequiredClover = expArr[currentLevel-1];
        Double currentPoint = (currentClover-prevRequiredClover) / requiredClover;

        return new Pair<>(currentLevel, currentPoint);
    }

    /**
     * 대표 캐릭터 불러오기
    */
    @Override
    public MonsterRes searchRepresentativeMonster(Long memberId) {

        MemberMonsterProfile profile = profileRepository.findByMemberId(memberId).get();
        MemberMonsterGrowth growth = growthRepository.findByMemberMonsterProfile_MemberIdAndMonsterType_TypeId(profile.getMemberId(), profile.getMonsterInfo().getMonsterType().getTypeId()).get();
        MonsterType type = profile.getMonsterInfo().getMonsterType(); //프로필 타입

        // exp 계산
        int currentClover = growth.getMonsterClover();
        Pair<Integer, Double> pair = getCurrentPoint(currentClover);
        // result
        MonsterRes mosterRes = MonsterMapper.INSTANCE.toRepresentativeMonsterRes(profile, growth, pair.second());

        return mosterRes;
    }

    /**
     * 보유중인 캐릭터 불러오기(도감)
     */
    @Override
    public List<MonsterRes> searchMonsterList(Long memberId){

        List<MemberMonsterGrowth> monsterList = growthRepository.findAllByMemberMonsterProfile_MemberId(memberId);

        List<MonsterRes> resList = monsterList
                .stream().map(m -> MonsterMapper.INSTANCE.toMonsterListRes(m, getCurrentPoint(m.getMonsterClover()).first())
                ).collect(Collectors.toList());

        return resList;
    }

    /**
     * 클로버 조회하기
     */
    @Override
    public CloverRes searchCloverInfo(Long memberId) {

        MemberMonsterProfile profile = profileRepository.findByMemberId(memberId).get();

        CloverRes cloverRes = CloverMapper.INSTANCE.toCloverRes(profile);

        return cloverRes;
    }


    /**
     * 경험치 등록하기
     */
    @Override
    public MonsterRes updateMonsterClover(Long memberMonsterId, int clover) {

        MemberMonsterGrowth growth = growthRepository.findByMemberMonsterId(memberMonsterId).get();
        MemberMonsterProfile profile = growth.getMemberMonsterProfile();
        if(profile.getMemberClover() < clover) {
            //클로버 부족
            throw new CustomException(ErrorCode.SHORTAGE_OF_CLOVER);
        }
        growth.addMonsterClover(clover);
        profile.removeMemberClover(clover);
        growthRepository.save(growth);
        profileRepository.save(profile);

        // exp 계산
        int currentClover = growth.getMonsterClover();
        Pair<Integer, Double> pair = getCurrentPoint(currentClover);

        //result
        MonsterRes monsterRes = MonsterMapper.INSTANCE.toLevelUpMonsterRes(growth, pair.first(), pair.second());

        return monsterRes;
    }

    /**
     * 초기 소소몬 등록
     * - 회원가입 시 Growth에 레벨1 3개 세팅
     */
    @Override
    public void setInitialMonster(Long memberId) {
        // 회원에서 memberId 받아서 -> 정합성 체크..??..

        // 프로필 저장
        MonsterInfo info = infoRepository.findByMonsterId(1).get();

        MemberMonsterProfile profile = MemberMonsterProfile.builder()
                .memberId(memberId)
                .memberClover(0)
                .memberAccruedClover(0L)
                .monsterInfo(info)
                .build();

        profileRepository.save(profile);

        // 타입마다 growth 생성
        List<MonsterType> types = typeRepository.findAll();

        for(MonsterType t : types) {
            MemberMonsterGrowth growth = MemberMonsterGrowth.builder()
                    .memberMonsterProfile(profile)
                    .monsterType(t)
                    .monsterClover(0)
                    .build();
            growthRepository.save(growth);
        }

    }

    @Override
    public void updateClover(Long fromMemberId, Long toMemberId, int clover) {

        MemberMonsterProfile fromProfile = profileRepository.findByMemberId(fromMemberId).get();
        MemberMonsterProfile toProfile = profileRepository.findByMemberId(toMemberId).get();

        fromProfile.addMemberClover(clover);
        fromProfile.addMemberAccruedClover(clover);
        toProfile.addMemberClover(clover);
        toProfile.addMemberAccruedClover(clover);

        profileRepository.save(fromProfile);
        profileRepository.save(toProfile);

    }

}