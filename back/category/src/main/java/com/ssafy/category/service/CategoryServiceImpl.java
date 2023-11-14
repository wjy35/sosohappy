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
    public List<CategoryRes> getDefaultCategoryList() {
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
        List<CategoryPick> pickList = pickRepository.findRecentListByMemberId(memberId);

        List<CategoryRes> categoryResList = pickList
                .stream().map(m -> CategoryMapper.INSTANCE.toCategoryRes(m.getCategory()))
                .collect(Collectors.toList());

        return categoryResList;
    }
    /**
     * help-history 이벤트 발생 시 pick에 추가하기
     */
    @Override
    @Transactional
    public void addCategoryPick(Long toMemberId, Long category_id) {

        // 예외처리?? categorㅛ
        Category category = categoryRepository.findByCategoryId(category_id).get();

        CategoryPick init = CategoryMapper.INSTANCE.toPickEntity(toMemberId, category);

        CategoryPick pick = pickRepository.findByMemberIdAndCategory_CategoryId(toMemberId, category_id)
                .orElseGet(() -> pickRepository.save(init));

        pick.addPickCount();

        pickRepository.save(pick);
    }


}
