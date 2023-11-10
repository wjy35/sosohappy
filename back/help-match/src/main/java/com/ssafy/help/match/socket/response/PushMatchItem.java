package com.ssafy.help.match.socket.response;

import com.ssafy.help.match.socket.dto.CategoryDTO;
import lombok.Data;

@Data
public class PushMatchItem {
    Long memberId;
    String nickname;
    CategoryDTO category;
    Double latitude;
    Double longitude;
    String content;
    String place;
    Double distance;
}
