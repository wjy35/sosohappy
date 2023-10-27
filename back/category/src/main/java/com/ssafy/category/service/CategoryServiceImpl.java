package com.ssafy.category.service;

import com.ssafy.category.common.exception.CustomException;
import com.ssafy.category.common.exception.ErrorCode;
import com.ssafy.category.domain.entity.Category;
import com.ssafy.category.domain.mapper.CategoryMapper;
import com.ssafy.category.domain.res.CategoryRes;
import com.ssafy.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryRes getCategoryInfo(Long categoryId) {
        Category category = categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
        return CategoryMapper.INSTANCE.toCategoryRes(category);
    }

    @Override
    public List<CategoryRes> getdefaultCategoryList() {
        List<Category> categoryList = categoryRepository.findAll();

        List<CategoryRes> categoryResList = categoryList
                .stream().map(m -> CategoryMapper.INSTANCE.toCategoryRes(m))
                .collect(Collectors.toList());

        return categoryResList;
    }
}
