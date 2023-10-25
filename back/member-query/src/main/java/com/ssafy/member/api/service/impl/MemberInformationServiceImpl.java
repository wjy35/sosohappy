package com.ssafy.member.api.service.impl;

import com.ssafy.member.api.service.MemberInformationService;
import com.ssafy.member.db.entity.MemberEntity;
import com.ssafy.member.db.repository.MemberEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberInformationServiceImpl implements MemberInformationService {
    private final MemberEntityRepository memberEntityRepository;

    @Override
    public MemberEntity getInformationByMemberId(Long memberId) {
        return memberEntityRepository.findByMemberId(memberId);
    }

    @Override
    public Boolean isNicknameAvailable(String nickname) {
        return memberEntityRepository.existsByNickname(nickname);
    }

    @Override
    public Boolean isMemberSignIdAvailable(String memberSignId) {
        return Optional.of(!memberEntityRepository.existsByMemberSignId(memberSignId)).get();
    }
}
