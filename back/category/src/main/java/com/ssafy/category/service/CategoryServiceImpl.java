package com.ssafy.category.service;

import com.ssafy.category.common.exception.CustomException;
import com.ssafy.category.common.exception.ErrorCode;
import com.ssafy.category.domain.entity.Category;
import com.ssafy.category.domain.entity.CategoryPick;
import com.ssafy.category.domain.mapper.CategoryMapper;
import com.ssafy.category.domain.res.CategoryRes;
import com.ssafy.category.repository.CategoryRepository;
import com.ssafy.category.repository.PickRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final PickRepository pickRepository;

    /**
     * categoryId로 찾기 카테고리 정보 찾기 (1개)
     */
    @Override
    public CategoryRes getCategoryInfo(Long categoryId) {
        Category category = categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
        return CategoryMapper.INSTANCE.toCategoryRes(category);
    }

    /**
     * 카테고리 목록 조회(default)
     */
    @Override
    public List<CategoryRes> getdefaultCategoryList() {
        List<Category> categoryList = categoryRepository.findAll();

        List<CategoryRes> categoryResList = categoryList
                .stream().map(m -> CategoryMapper.INSTANCE.toCategoryRes(m))
                .collect(Collectors.toList());

        return categoryResList;
    }

    /**
     * 카테고리 목록 조회(recent)
     */
    @Override
    public List<CategoryRes> getRecentCategoryList(Long memberId) {
        List<CategoryPick> pickList = pickRepository.findTop5ByMemberIdOrderByPickTimeDesc(memberId);

        List<CategoryRes> categoryResList = pickList
                .stream().map(m -> CategoryMapper.INSTANCE.toCategoryRes(m.getCategory()))
                .collect(Collectors.toList());

        return categoryResList;
    }
}
