package com.ssafy.help.match.db.repository;

public interface MemberMatchSetRepository {
    void save(Long memberId,Long matchedMember);
    void delete(Long memberId,Long matchedMember);
}
