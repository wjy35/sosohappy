package com.ssafy.help.match.socket.response;

import lombok.*;

import java.util.List;

@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PushMatchListResponse {
    @Builder.Default
    MatchListCommand matchListCommand = MatchListCommand.PUSH;
    List<PushMatchItem> receiveMatchList;
}
