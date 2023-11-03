package com.ssafy.help.match.socket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchEventDTO {
    Long memberId;
    Long matchedMemberId;
}
