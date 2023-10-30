package com.ssafy.category.service;

import com.ssafy.category.domain.res.CategoryRes;

import java.util.List;

public interface CategoryService {

    CategoryRes getCategoryInfo(Long categoryId);

    List<CategoryRes> getdefaultCategoryList();
    List<CategoryRes> getRecentCategoryList(Long memberId);
}
