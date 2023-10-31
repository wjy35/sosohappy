package com.ssafy.monster.domain.topic.member;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    @JsonProperty("member_id")
    public Long memberId;
    @JsonProperty("member_sign_id")
    public String memberSignId;
    @JsonProperty("member_sign_password")
    public String memberSignPassword;
    @JsonProperty("nickname")
    public String nickname;
    @JsonProperty("name")
    public String name;
    @JsonProperty("profile_monster_id")
    public int profileMonsterId;
    @JsonProperty("profile_background_id")
    public Long profileBackgroundId;
    @JsonProperty("disabled")
    public Long disabled;
    @JsonProperty("gender")
    public Long gender;
    @JsonProperty("created_at")
    public Timestamp createdAt;

}