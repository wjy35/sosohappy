package com.ssafy.member.consumer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MemberDTO {
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
