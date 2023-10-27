package com.ssafy.member.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {
    Long memberId;
    String memberSignId;
    String memberSignPassword;
    String nickname;
    String name;
    Integer profileMonsterId;
    Integer profileBackgroundId;
    Boolean disabled;
    Integer gender;
    Timestamp createdAt;
}
