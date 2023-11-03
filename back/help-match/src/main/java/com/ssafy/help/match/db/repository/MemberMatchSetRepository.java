package com.ssafy.help.match.db.repository;

import java.util.Set;

public interface MemberMatchSetRepository {
    void save(Long memberId,Long matchedMember);
    void delete(Long memberId,Long matchedMember);

    Set<String> getSet(Long memberId);
}
