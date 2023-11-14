package com.ssafy.category.repository;

import com.ssafy.category.domain.entity.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("local")
@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @DisplayName("categoryRepository의 findAll 메서드는 항상 결과값이 20이다.")
    @Test
    void categoryRepositoryFindAllIsAlwaysTwenty() {

        List<Category> result = categoryRepository.findAll();

        Assertions.assertThat(result).hasSize(20);

    }
}