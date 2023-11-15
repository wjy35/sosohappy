package com.ssafy.member.api.response;

import lombok.Data;

@Data
public class MemberPublicInformationResponse {
    Long memberId;
    String nickname;
    Integer profileMonsterId;
    Integer profileBackgroundId;
    Boolean disabled;
}
