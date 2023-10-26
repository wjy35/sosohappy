package com.ssafy.category.controller;

import com.ssafy.category.common.response.FormattedResponse;
import com.ssafy.category.domain.entity.Category;
import com.ssafy.category.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    private final CategoryServiceImpl categoryServiceImpl;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @GetMapping("/getCategoryInfo/{categoryId}")
    public ResponseEntity<FormattedResponse> getCategoryInfo(@PathVariable Long categoryId) {
        Category category = categoryServiceImpl.getCategoryInfo(categoryId);

        FormattedResponse formattedResponse = FormattedResponse.builder()
                .status("success")
                .message("카테고리 정보를 성공적으로 불러왔습니다.")
                .result("category",category)
                .build();

        return new ResponseEntity<>(formattedResponse, HttpStatus.OK);
    }
}
