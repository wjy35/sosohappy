package com.ssafy.member.api.service.impl;

import com.ssafy.member.api.service.MemberManageService;
import com.ssafy.member.db.entity.MemberEntity;
import com.ssafy.member.db.repository.MemberEntityRepository;
import com.ssafy.member.util.HashUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberManageServiceImpl implements MemberManageService {
    private final MemberEntityRepository memberEntityRepository;
    private final HashUtil hashUtil;
    @Override
    public void modify(MemberEntity memberEntity) {
        Optional.ofNullable(memberEntity.getMemberSignPassword())
                .ifPresent((password)->{
                    memberEntity.setMemberSignPassword(hashUtil.hash(password));
                });
        memberEntityRepository.save(memberEntity);
    }
}
