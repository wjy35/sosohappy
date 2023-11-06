package com.ssafy.help.match.db.repository;

import com.ssafy.help.match.db.entity.SendMatchEntity;

import java.util.Set;

public interface SendMatchEntityRepository {
    void save(SendMatchEntity sendMatchEntity);

    SendMatchEntity findByMemberId(Long memberId);

    void add(Long memberId,Long matchedMemberId);
    boolean isMember(Long memberId,Long matchedMemberId);

    Set<String> findSetByMemberId(Long memberId);

    void deleteSet(Long memberId);
}
