package com.ssafy.helphistoryquery.db.repository;

import com.ssafy.helphistoryquery.db.entity.CategoryEntity;

public interface CategoryRepository {
    CategoryEntity getCategoryInfo(long categoryId);
}
