package com.ssafy.monster.service;

import com.ssafy.monster.common.exception.CustomException;
import com.ssafy.monster.common.exception.ErrorCode;
import com.ssafy.monster.domain.entity.MemberMonsterProfile;
import com.ssafy.monster.repository.ProfileRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("local")
@SpringBootTest
class MonsterServiceImplTest {

    @Autowired
    private ProfileRepository profileRepository;

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


}