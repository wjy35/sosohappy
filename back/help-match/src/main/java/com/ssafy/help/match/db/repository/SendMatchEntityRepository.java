package com.ssafy.help.match.db.repository;

import com.ssafy.help.match.db.entity.SendMatchEntity;

public interface SendMatchEntityRepository {
    void save(SendMatchEntity sendMatchEntity);

    SendMatchEntity findByMemberId(Long memberId);
}
