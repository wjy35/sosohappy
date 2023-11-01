package com.ssafy.category.domain.mapper;

import com.ssafy.category.domain.entity.Category;
import com.ssafy.category.domain.entity.CategoryPick;
import com.ssafy.category.domain.res.CategoryRes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryRes toCategoryRes(Category category);
    CategoryPick toPickEntity(Long memberId, Category category);

}
