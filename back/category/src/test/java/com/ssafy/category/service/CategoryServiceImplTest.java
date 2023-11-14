package com.ssafy.category.service;

import com.ssafy.category.common.exception.CustomException;
import com.ssafy.category.common.exception.ErrorCode;
import com.ssafy.category.domain.entity.Category;
import com.ssafy.category.domain.entity.CategoryPick;
import com.ssafy.category.domain.res.CategoryRes;
import com.ssafy.category.repository.CategoryRepository;
import com.ssafy.category.repository.PickRepository;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@Transactional(readOnly = true)
@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PickRepository pickRepository;

    /**
     * category_id = 1 ~ 20
     * toMemberId = 23, 25
     */

    @DisplayName("CategoryPick이 없어도 예외를 발생시키지 않는다.")
    @Test
    void emptyCategoryPick() {

        // when
        CategoryPick pick = pickRepository.findByMemberIdAndCategory_CategoryId(23L, 1L)
                .orElseGet(() ->
                        CategoryPick.builder()
                                .pickId(1L)
                                .memberId(23L)
                                .pickCount(0)
                        .build());

        // then
        Assertions.assertThat(pick).isNotNull();

    }


    @DisplayName("Category가 없을 경우 Exception을 발생시킨다.")
    @Test
    void throwExpceptionWhenCategoryIsNull() {

        // given
        Long categoryId = 100000L;

        // when then

        Assertions.assertThatThrownBy(() -> categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new CustomException(ErrorCode.CATEGORY_NOT_FOUND)))
                .isInstanceOf(CustomException.class);

    }

}