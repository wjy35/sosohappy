package com.ssafy.help.match.socket.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HelpAcceptRequest {
    Long helperMemberId;
    Long disabledMemberId;
}
