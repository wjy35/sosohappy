package com.ssafy.member.api.service.impl;

import com.ssafy.member.api.service.MemberInformationService;
import com.ssafy.member.db.entity.MemberEntity;
import com.ssafy.member.db.repository.MemberEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberInformationServiceImpl implements MemberInformationService {
    private final MemberEntityRepository memberEntityRepository;

    @Override
    public MemberEntity getInformationByMemberId(Long memberId) {
        return memberEntityRepository.findByMemberId(memberId);
    }

    @Override
    public boolean isNicknameAvailable(String nickname) {
        return memberEntityRepository.existsByNickname(nickname);
    }
}
