package com.ssafy.category.controller;

import com.ssafy.category.common.response.FormattedResponse;
import com.ssafy.category.domain.res.CategoryRes;
import com.ssafy.category.service.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryServiceImpl categoryServiceImpl;

    @GetMapping("/getCategoryInfo/{categoryId}")
    public ResponseEntity<FormattedResponse> getCategoryInfo(@PathVariable Long categoryId) {
        CategoryRes categoryRes = categoryServiceImpl.getCategoryInfo(categoryId);

        FormattedResponse formattedResponse = FormattedResponse.builder()
                .status("success")
                .message("카테고리 정보를 성공적으로 불러왔습니다.")
                .result("category",categoryRes)
                .build();

        return new ResponseEntity<>(formattedResponse, HttpStatus.OK);
    }

    @GetMapping("/default")
    public ResponseEntity<FormattedResponse> getCategoryList(@RequestHeader Long memberId) {
        List<CategoryRes> defaultCategoryResList = categoryServiceImpl.getdefaultCategoryList();

        FormattedResponse formattedResponse = FormattedResponse.builder()
                .status("success")
                .message("카테고리 정보를 성공적으로 불러왔습니다.")
                .result("defaultCategoryList",defaultCategoryResList)
                .build();

        return new ResponseEntity<>(formattedResponse, HttpStatus.OK);
    }

    @GetMapping("/recent")
    public ResponseEntity<FormattedResponse> getRecentCategoryList(@RequestHeader Long memberId) {
        List<CategoryRes> recentCategoryResList = categoryServiceImpl.getRecentCategoryList(memberId);

        FormattedResponse formattedResponse = FormattedResponse.builder()
                .status("success")
                .message("카테고리 정보를 성공적으로 불러왔습니다.")
                .result("recentCategoryList", recentCategoryResList)
                .build();

        return new ResponseEntity<>(formattedResponse, HttpStatus.OK);
    }
}
