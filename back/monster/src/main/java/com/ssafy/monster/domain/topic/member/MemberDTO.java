package com.ssafy.monster.domain.topic.member;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberDTO {

    public Long memberId;
    public String memberSignId;
    public String memberSignPassword;
    public String nickname;
    public String name;
    public int profileMonsterId;
    public Long profileBackgroundId;
    public Long disabled;
    public Long gender;
    public Timestamp createdAt;

}