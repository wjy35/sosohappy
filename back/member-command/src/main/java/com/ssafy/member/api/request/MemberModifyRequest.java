package com.ssafy.member.api.request;

import lombok.Data;

@Data
public class MemberModifyRequest {
    String name;
    String nickname;
    String memberSignPassword;
    Boolean disabled;
    Integer gender;
}
