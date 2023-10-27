package com.ssafy.helphistoryquery.db.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.helphistoryquery.db.entity.CategoryEntity;
import com.ssafy.helphistoryquery.db.repository.CategoryRepository;
import com.ssafy.helphistoryquery.exception.CustomException;
import com.ssafy.helphistoryquery.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final HashOperations<String, String, Object> hashOps;

    private final ObjectMapper objectMapper;

    @Override
    public CategoryEntity getCategoryInfo(long categoryId) {
              String storedJson = (String) hashOps.get("category", String.valueOf(categoryId));

        try {
            return objectMapper.readValue(storedJson, CategoryEntity.class);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.NOT_FOUND_CATEGORY);
        }
    }
}
