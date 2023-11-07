package com.ssafy.help.match.socket.response;

import com.ssafy.help.match.db.entity.HelpMatchStatus;
import com.ssafy.help.match.db.entity.HelpMatchType;
import lombok.*;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchStatusResponse {
    HelpMatchType helpMatchType;
    HelpMatchStatus helpMatchStatus;

    @Singular("data")
    Map<String,Object> data;
}
