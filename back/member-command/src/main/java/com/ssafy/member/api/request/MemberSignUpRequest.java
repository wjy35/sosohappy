package com.ssafy.member.api.request;

import lombok.Data;

@Data
public class MemberSignUpRequest {
    String memberSignId;
    String memberSignPassword;
    String name;
    String nickname;
    Boolean disabled;
    Integer gender;
}
