package com.ssafy.observer.db.repository;

import java.util.Set;

public interface SendMatchEntityRepository {
    public Set<Long> getAndDeleteReceiveMemberIdSet(Long sendMemberId);
}
