package com.ssafy.help.match.socket.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PopMatchListResponse {
    @Builder.Default
    MatchListCommand matchListCommand = MatchListCommand.POP;
    List<Long> memberIdList;
}
