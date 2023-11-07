package com.ssafy.help.match.socket.response;

import com.ssafy.help.match.db.entity.HelpMatchStatus;
import com.ssafy.help.match.db.entity.HelpMatchType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchStatusResponse {
    HelpMatchType helpMatchType;
    HelpMatchStatus helpMatchStatus;
    Map<String,Object> data;
}
