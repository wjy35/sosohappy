package com.ssafy.notification.cloud.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Optional;

@Data
public class MemberResponse {

    Result result;

    public String getNickname(){

        if(Optional.ofNullable(result).isEmpty()) return "(알수없음)";
        if(Optional.ofNullable(result.member).isEmpty()) return "(알수없음)";

        return result.member.nickname;
    }

    private static class Result{
        @JsonProperty("member")
        private Member member;

        private Member getMember() {
            return member;
        }

        private void setMember(Member member) {
            this.member = member;
        }
    }

    private static class Member{
        private String nickname;
        static final Member defaultMember = new Member("(알수없음)");

        private String getNickname() {
            return nickname;
        }

        private void setNickname(String nickname) {
            this.nickname = nickname;
        }

        private Member(String nickname) {
            this.nickname = nickname;
        }

        private Member() {}
    }
}
