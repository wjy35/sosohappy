package com.ssafy.category.repository;

import com.ssafy.category.domain.entity.Category;
import com.ssafy.category.domain.entity.CategoryPick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickRepository extends JpaRepository<CategoryPick, Long> {
    List<CategoryPick> findTop5ByMemberIdOrderByPickTimeDesc(Long memberId);
}
