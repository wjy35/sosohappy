package com.ssafy.category.repository;

import com.ssafy.category.domain.entity.CategoryPick;

import java.util.List;

public interface PickCustomRepository {

    public List<CategoryPick> findRecentListByMemberId(Long memberId);

}
