package com.ssafy.member.api.service.impl;

import com.ssafy.member.api.service.MemberManageService;
import com.ssafy.member.db.entity.MemberEntity;
import com.ssafy.member.db.repository.MemberEntityRepository;
import com.ssafy.member.util.HashUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberManageServiceImpl implements MemberManageService {
    private final MemberEntityRepository memberEntityRepository;
    private final HashUtil hashUtil;

    @Override
    @Transactional
    public void modify(MemberEntity memberEntity) {
        MemberEntity target = memberEntityRepository.findById(memberEntity.getMemberId()).get();

        setMemberEntity(memberEntity,target);
    }

    private void setMemberEntity(MemberEntity source,MemberEntity target){
        Optional.ofNullable(source.getNickname())
                .ifPresent((nickname)->{target.setNickname(nickname);});

        Optional.ofNullable(source.getMemberSignPassword())
                .ifPresent((newPassword)->{
                    target.setMemberSignPassword(hashUtil.hash(newPassword));
                });

        Optional.ofNullable(source.getDisabled())
                .ifPresent((disabled)->{target.setDisabled(disabled);});

        Optional.ofNullable(source.getGender())
                .ifPresent((gender)->{target.setGender(gender);});

        Optional.ofNullable(source.getProfileMonsterId())
                .ifPresent((profileMonsterId)->{target.setProfileMonsterId(profileMonsterId);});

        Optional.ofNullable(source.getProfileBackgroundId())
                .ifPresent((profileBackgroundId)->{target.setProfileBackgroundId(profileBackgroundId);});
    }


}

