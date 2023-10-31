package com.ssafy.category.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.category.domain.entity.CategoryPick;
import com.ssafy.category.domain.entity.QCategoryPick;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PickQueryRepository {

    private final JPAQueryFactory queryFactory;
    public List<CategoryPick> findRecentListByMemberId(Long memberId) {
        QCategoryPick pick = QCategoryPick.categoryPick;

        return queryFactory.selectFrom(pick)
                .where(pick.memberId.eq(memberId))
                .groupBy(pick.category.categoryId)
                .orderBy(pick.pickTime.desc())
                .limit(5)
                .fetch();

    }
}
