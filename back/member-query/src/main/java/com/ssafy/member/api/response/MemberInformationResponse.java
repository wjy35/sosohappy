package com.ssafy.member.api.response;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class MemberInformationResponse {
    Long memberId;
    String memberSignId;
    String nickname;
    String name;
    Integer profileMonsterId;
    Integer profileBackgroundId;
    Boolean disabled;
    Integer gender;
    Timestamp createdAt;
}
