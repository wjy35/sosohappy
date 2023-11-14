package com.ssafy.category.controller;

import com.ssafy.category.domain.entity.Category;
import com.ssafy.category.service.CategoryService;
import com.ssafy.category.service.CategoryServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("local")
@WebMvcTest(controllers = CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryServiceImpl categoryService;

    @DisplayName("categoryInfo를 가져오면 status와 message는 성공적이어야 한다.")
    @Test
    void getCategoryInfo() throws Exception {

        Long categoryId = 10L;

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/getCategoryInfo/" + categoryId)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("카테고리 정보를 성공적으로 불러왔습니다."))
        ;
    }

    @DisplayName("유효하지 않은 categoryInfo를 요청받은 경우 status와 message는 실패여야 한다.")
    @Test
    void getInvalidCategoryInfo() throws Exception {

        Long categoryId = 100000L;

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/getCategoryInfo/" + categoryId)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("fail"))
                .andExpect(jsonPath("$.message").value("categoryId가 존재하지 않습니다."))
                .andExpect(jsonPath("$.result").isEmpty())
        ;
    }

    @DisplayName("getDefaultCateoryList를 가져오는 경우 항상 성공해야 한다.")
    @Test
    void getDefaultCategory() throws Exception {

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/default")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.message").value("카테고리 정보를 성공적으로 불러왔습니다."))
                ;
    }
}