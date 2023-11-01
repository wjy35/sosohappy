package com.ssafy.category.repository;

import com.ssafy.category.domain.entity.CategoryPick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PickRepository extends JpaRepository<CategoryPick, Long>, PickCustomRepository {

    Optional<CategoryPick> findByMemberIdAndCategory_CategoryId(Long memberId, Long categoryId);

}

