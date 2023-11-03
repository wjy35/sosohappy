package com.ssafy.member.consumer.event;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ssafy.member.consumer.dto.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberEvent {
    String op;
    MemberDTO before;
    MemberDTO after;
}
