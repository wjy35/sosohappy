package com.ssafy.observer.db.repository;

import java.util.Set;

public interface SendMemberIdSetRepository {
    void save(Long memberId,Long matchedMember);
    void delete(Long memberId,Long matchedMember);
    Set<Long> getSet(Long memberId);
}
