package com.ssafy.member.api.service.impl;

import com.ssafy.member.api.service.MemberManageService;
import com.ssafy.member.db.entity.MemberEntity;
import com.ssafy.member.db.repository.MemberEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberManageServiceImpl implements MemberManageService {
    private final MemberEntityRepository memberEntityRepository;

    @Override
    public void modify(MemberEntity memberEntity) {
        memberEntityRepository.save(memberEntity);
    }
}
