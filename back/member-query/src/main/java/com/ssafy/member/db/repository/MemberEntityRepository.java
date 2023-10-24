package com.ssafy.member.db.repository;

import com.ssafy.member.db.entity.MemberEntity;

public interface MemberEntityRepository {
    MemberEntity findByMemberId(Long memberId);
    Boolean existsByNickname(String nickname);
}
