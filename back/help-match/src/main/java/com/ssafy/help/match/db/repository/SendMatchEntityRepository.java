package com.ssafy.help.match.db.repository;

import com.ssafy.help.match.db.entity.SendMatchEntity;

import java.util.Set;

public interface SendMatchEntityRepository {
    void save(SendMatchEntity sendMatchEntity);

    SendMatchEntity findByMemberId(Long memberId);

    SendMatchEntity getAndDeleteByMemberId(Long memberId);
}
