package com.ssafy.member.api.service;

import com.ssafy.member.db.entity.MemberEntity;

public interface MemberInformationService {
    MemberEntity getInformationByMemberId(Long memberId);
    Boolean isNicknameAvailable(String nickname);
}
