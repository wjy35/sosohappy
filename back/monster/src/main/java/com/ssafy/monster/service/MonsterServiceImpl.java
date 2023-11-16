package com.ssafy.monster.service;

import com.ssafy.monster.common.exception.CustomException;
import com.ssafy.monster.common.exception.ErrorCode;
import com.ssafy.monster.domain.entity.MemberMonsterGrowth;
import com.ssafy.monster.domain.entity.MemberMonsterProfile;
import com.ssafy.monster.domain.entity.MonsterInfo;
import com.ssafy.monster.domain.entity.MonsterType;
import com.ssafy.monster.domain.mapper.GrowthMapper;
import com.ssafy.monster.domain.mapper.ProfileMapper;
import com.ssafy.monster.domain.res.CloverRes;
import com.ssafy.monster.domain.res.LevelInfo;
import com.ssafy.monster.domain.res.MonsterRes;
import com.ssafy.monster.domain.mapper.CloverMapper;
import com.ssafy.monster.domain.mapper.MonsterMapper;
import com.ssafy.monster.repository.GrowthRepository;
import com.ssafy.monster.repository.InfoRepository;
import com.ssafy.monster.repository.ProfileRepository;
import com.ssafy.monster.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.text.ChoiceFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MonsterServiceImpl implements MonsterService{

    private final ProfileRepository profileRepository;
    private final GrowthRepository growthRepository;
    private final InfoRepository infoRepository;
    private final TypeRepository typeRepository;

    /**
     * 경험치 구간 가져오기
     */
    private double[] expArr; //누적 경험치
    private String[] levelArr;

    private ChoiceFormat cf;

    @PostConstruct
    private void getExpinfo() {
        List<Integer> expList = infoRepository.getMonsterCloverInfo();
        expArr = expList.stream().mapToDouble(i -> i).toArray();

        List<Integer> levelList = infoRepository.getMonsterLevels();
        levelArr = levelList.stream().map(i -> i.toString()).toArray(size -> new String[size]);

        cf = new ChoiceFormat(expArr, levelArr);
    }

    private LevelInfo getCurrentPoint(int monsterClover){
        int currentLevel = Integer.parseInt(cf.format(monsterClover));
        if(currentLevel == expArr.length){
            return new LevelInfo(currentLevel,0,0,1.0);
        }
        int requiredClover = (int) (expArr[currentLevel] - expArr[currentLevel-1]);
        Double prevRequiredClover = expArr[currentLevel-1];
        int currentClover = (int) (monsterClover-prevRequiredClover);

        Double currentCloverRate = (monsterClover-prevRequiredClover) / requiredClover;

        return new LevelInfo(currentLevel, requiredClover, currentClover, currentCloverRate);
    }

    /**
     * 대표 캐릭터 불러오기
    */
    @Override
    public MonsterRes searchRepresentativeMonster(Long memberId) {

        MemberMonsterProfile profile = profileRepository.findByMemberId(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        MemberMonsterGrowth growth = growthRepository
                .findByMemberMonsterProfile_MemberIdAndMonsterType_TypeId(profile.getMemberId(), profile.getMonsterInfo().getMonsterType().getTypeId())
                .orElseThrow(() -> new CustomException(ErrorCode.GROWTH_NOT_FOUND));
        MonsterType type = profile.getMonsterInfo().getMonsterType(); //프로필 타입

        // exp 계산
        int currentClover = growth.getMonsterClover();
        LevelInfo levelInfo = getCurrentPoint(currentClover);
        // result
        MonsterRes monsterRes = MonsterMapper.INSTANCE.toRepresentativeMonsterRes(profile, growth, levelInfo);

        return monsterRes;
    }

    /**
     * 보유중인 캐릭터 불러오기(도감)
     */
    @Override
    public List<MonsterRes> searchMonsterList(Long memberId){

        List<MemberMonsterGrowth> monsterList = growthRepository.findAllByMemberMonsterProfile_MemberId(memberId);

        List<MonsterRes> resList = monsterList
                .stream().map(m -> MonsterMapper.INSTANCE.toMonsterRes(m, getCurrentPoint(m.getMonsterClover()))
                ).collect(Collectors.toList());

        return resList;
    }

    /**
     * 클로버 조회하기
     */
    @Override
    public CloverRes searchCloverInfo(Long memberId) {

        MemberMonsterProfile profile = profileRepository.findByMemberId(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        CloverRes cloverRes = CloverMapper.INSTANCE.toCloverRes(profile);

        return cloverRes;
    }

    /**
     * 경험치 등록하기
     */
    @Override
    @Transactional
    public MonsterRes updateMonsterClover(Long memberMonsterId, int clover) {

        MemberMonsterGrowth growth = growthRepository.findByMemberMonsterId(memberMonsterId)
                .orElseThrow(() -> new CustomException(ErrorCode.GROWTH_NOT_FOUND));
        MemberMonsterProfile profile = growth.getMemberMonsterProfile();
        if(profile.getMemberClover() < clover) {
            throw new CustomException(ErrorCode.SHORTAGE_OF_CLOVER);
        } else if(growth.getMonsterClover() >= expArr[expArr.length -1]) {
            throw new CustomException(ErrorCode.FULL_OF_CLOVER);
        } else if(growth.getMonsterClover() + clover > expArr[expArr.length -1]) {
            clover = (int) expArr[expArr.length -1] - growth.getMonsterClover();
        }
        growth.addMonsterClover(clover);
        profile.removeMemberClover(clover);
        growthRepository.save(growth);
        profileRepository.save(profile);

        // exp 계산
        int currentClover = growth.getMonsterClover();
        LevelInfo levelInfo = getCurrentPoint(currentClover);

        //result
        MonsterRes monsterRes = MonsterMapper.INSTANCE.toMonsterRes(growth, levelInfo);

        return monsterRes;
    }

    /**
     * 초기 소소몬 등록
     * - 회원가입 시 Growth에 레벨1 3개 세팅
     */
    @Override
    @Transactional
    public void setInitialMonster(Long memberId) {
        if(profileRepository.existsByMemberId(memberId)){
            throw new CustomException(ErrorCode.MEMBER_EXIST_ERROR);
        }

        //프로필 저장
        MonsterInfo info = infoRepository.findByMonsterId(1).get();

        MemberMonsterProfile profile = ProfileMapper.INSTANCE.toProfileEntity(memberId, 0, 0L, info);

        profileRepository.save(profile);

        // 타입마다 growth 생성
        List<MonsterType> types = typeRepository.findAll();

        for(MonsterType t : types) {
            if(t.getTypeId() == types.size()) continue;
            growthRepository.save(GrowthMapper.INSTANCE.toGrowthEntity(profile, t, 0));
        }

    }

    @Override
    @Transactional
    public void updateClover(Long fromMemberId, int clover) {

        MemberMonsterProfile fromProfile = profileRepository.findByMemberId(fromMemberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        fromProfile.addMemberClover(clover);
        fromProfile.addMemberAccruedClover(clover);

        profileRepository.save(fromProfile);

    }

    /**
     * 프로필 변경
     */
    @Override
    @Transactional
    public void updateMemberMonsterProfile(Long memberId, int profileMonsterId) {

        MemberMonsterProfile profile = profileRepository.findByMemberId(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        MonsterInfo info = infoRepository.findByMonsterId(profileMonsterId)
                .orElseThrow(() -> new CustomException(ErrorCode.MONSTER_NOT_FOUND));
        MemberMonsterGrowth growth = growthRepository.findByMemberMonsterProfile_MemberIdAndMonsterType_TypeId(memberId, info.getMonsterType().getTypeId()).get();

        int currentLevel = getCurrentPoint(growth.getMonsterClover()).getCurrentLevel();
        if(currentLevel < info.getMonsterLevel()) {
            throw new CustomException(ErrorCode.GROWTH_NOT_FOUND);
        }

        profile.setMonsterInfo(info);
        profileRepository.save(profile);
    }

    @Override
    @Transactional
    public void deleteMemberMonsterProfile(Long memberId) {
        if(!profileRepository.existsByMemberId(memberId)){
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
        } else {
            growthRepository.deleteByMemberMonsterProfile_MemberId(memberId);
            profileRepository.deleteByMemberId(memberId);
        }
    }

    @Override
    @Transactional
    public boolean checkMaxLevel(Long memberId) {
        List<MemberMonsterGrowth> growthList = growthRepository.findAllByMemberMonsterProfile_MemberId(memberId);

        for(MemberMonsterGrowth growth : growthList){
            if(growth.getMonsterType().getTypeId() == 4) return true;
            if(growth.getMonsterClover() < expArr[expArr.length-1]) return false;
        }

        MemberMonsterProfile profile = profileRepository.findByMemberId(memberId).get();
        MonsterType type = typeRepository.findByTypeId((byte) 4);
        growthRepository.save(GrowthMapper.INSTANCE.toGrowthEntity(profile, type, 0));

        return true;
    }

}
