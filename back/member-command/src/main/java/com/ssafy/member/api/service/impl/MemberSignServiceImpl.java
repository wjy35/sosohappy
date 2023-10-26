package com.ssafy.member.api.service.impl;

import com.ssafy.member.api.exception.CustomException;
import com.ssafy.member.api.exception.ErrorCode;
import com.ssafy.member.api.mapper.AuthTokenMapper;
import com.ssafy.member.api.request.SignInRequest;
import com.ssafy.member.api.response.SignInResponse;
import com.ssafy.member.api.service.MemberSignService;
import com.ssafy.member.db.entity.MemberEntity;
import com.ssafy.member.db.repository.MemberEntityRepository;
import com.ssafy.member.util.AuthTokenDTO;
import com.ssafy.member.util.HashUtil;
import com.ssafy.member.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberSignServiceImpl implements MemberSignService {
    private final MemberEntityRepository memberEntityRepository;
    private final JwtUtil jwtUtil;
    private final HashUtil hashUtil;

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) {
        MemberEntity memberEntity = memberEntityRepository.findByMemberSignId(signInRequest.getInputId())
                .orElseThrow(()->{throw new CustomException(ErrorCode.WRONG_ID);});

        Long memberId = memberEntity.getMemberId();
        String memberSignPassword = memberEntity.getMemberSignPassword();

        verifyPassword(signInRequest.getInputPassword(), memberSignPassword);

        return AuthTokenMapper.INSTANCE.toResponse(jwtUtil.generateAuthToken(memberId));
    }



    private void verifyPassword(String inputPassword,String storedPassword) {
        if(storedPassword.equals(hashUtil.hash(inputPassword))) return;

        throw new CustomException(ErrorCode.WRONG_PASSWORD);
    }
}
