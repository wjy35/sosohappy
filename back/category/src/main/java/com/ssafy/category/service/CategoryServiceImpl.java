package com.ssafy.category.service;

import com.ssafy.category.common.exception.CustomException;
import com.ssafy.category.common.exception.ErrorCode;
import com.ssafy.category.domain.entity.Category;
import com.ssafy.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategoryInfo(Long categoryId) {
        Optional<Category> category = categoryRepository.findByCategoryId(categoryId);
        category.orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
        return category.get();
    }
}
