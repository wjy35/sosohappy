package com.ssafy.category.service;

import com.ssafy.category.domain.res.CategoryRes;

import java.util.List;

public interface CategoryService {

    CategoryRes getCategoryInfo(Long categoryId);

    List<CategoryRes> getDefaultCategoryList();
    List<CategoryRes> getRecentCategoryList(Long memberId);
    void addCategoryPick(Long toMemberId, Long category_id);
}
