package com.ssafy.help.match.socket.response;

import lombok.*;

import java.util.List;

@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ReceiveMatchListResponse {
    List<ReceiveMatchItem> receiveMatchList;
}
