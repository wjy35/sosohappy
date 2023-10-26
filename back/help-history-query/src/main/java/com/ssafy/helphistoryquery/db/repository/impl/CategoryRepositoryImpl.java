package com.ssafy.helphistoryquery.db.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.helphistoryquery.db.entity.CategoryEntity;
import com.ssafy.helphistoryquery.db.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final HashOperations<String, String, Object> hashOps;

    private final ObjectMapper objectMapper;

    @Override
    public CategoryEntity getCategoryInfo(long categoryId) throws JsonProcessingException {
        String storedJson = (String) hashOps.get("category", String.valueOf(categoryId));

        return objectMapper.readValue(storedJson, CategoryEntity.class);
    }
}
