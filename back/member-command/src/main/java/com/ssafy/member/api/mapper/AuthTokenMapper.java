package com.ssafy.member.api.mapper;

import com.ssafy.member.api.response.SignInResponse;
import com.ssafy.member.util.AuthTokenDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthTokenMapper {
    AuthTokenMapper INSTANCE = Mappers.getMapper(AuthTokenMapper.class);

    SignInResponse toResponse(AuthTokenDTO authTokenDTO);
}
