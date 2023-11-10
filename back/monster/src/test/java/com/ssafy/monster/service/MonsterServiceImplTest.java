package com.ssafy.monster.service;

import com.netflix.discovery.shared.Pair;
import com.ssafy.monster.common.exception.CustomException;
import com.ssafy.monster.common.exception.ErrorCode;
import com.ssafy.monster.domain.entity.MemberMonsterGrowth;
import com.ssafy.monster.domain.entity.MemberMonsterProfile;
import com.ssafy.monster.domain.entity.MonsterInfo;
import com.ssafy.monster.repository.GrowthRepository;
import com.ssafy.monster.repository.InfoRepository;
import com.ssafy.monster.repository.ProfileRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.PostConstruct;
import java.text.ChoiceFormat;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("local")
@SpringBootTest
class MonsterServiceImplTest {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private InfoRepository infoRepository;

    @Autowired
    private GrowthRepository growthRepository;

    @Autowired
    private MonsterService monsterService;

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

    @Test
    @DisplayName("id에 존재하는 MemberMonsterProfile이 있는지 확인한다.")
    void findMonsterProfileById() {

        // given : 테스트할 아이디
        Long id = 1L;
        // when
        MemberMonsterProfile profile = profileRepository.findByMemberId(id).get();
        //then
        Assertions.assertThat(id).isEqualTo(profile.getMemberId());
    }

    @Test
    @DisplayName("id에 존재하는 MemberMonsterProfile이 없다면 Custom Exception을 발생시킨다.")
    void notFindMonsterProfileById() {

        // given : 테스트할 아이디
        Long id = 15235L;

        // when / then
        Assertions.assertThatThrownBy(() -> profileRepository.findByMemberId(id)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.MEMBER_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("이미 클로버가 꽉 차있는 상태에서(만렙) 레벨업 요청시 예외 발생시킨다.")
    void throwExceptionWhenCloverIsFull() {

        // given : 78개의 클로버를 가지고 있는 id
        MemberMonsterGrowth growth = growthRepository.findByMemberMonsterId(447L)
                .orElseThrow(() -> new CustomException(ErrorCode.GROWTH_NOT_FOUND));


        // when / then
        Assertions.assertThatThrownBy(() -> monsterService.updateMonsterClover
                (447L, 1))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.FULL_OF_CLOVER.getMessage());

    }

    @Test
    @DisplayName("현재 가지고 있는 클로버보다 요청한 클로버가 더 많은 경우 예외 발생시킨다.")
    void throwExceptionWhenRequestCloverIsMoreThanMyClover() {

        // given : 78개의 클로버를 가지고 있는 id
        MemberMonsterGrowth growth = growthRepository.findByMemberMonsterId(447L)
                .orElseThrow(() -> new CustomException(ErrorCode.GROWTH_NOT_FOUND));


        // when / then
        Assertions.assertThatThrownBy(() -> monsterService.updateMonsterClover
                        (447L, 25))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.SHORTAGE_OF_CLOVER.getMessage());

    }




}