package com.ssafy.help.match.socket.response;

import com.ssafy.help.match.socket.dto.CategoryDTO;
import lombok.Data;
import java.util.List;

@Data
public class ReceiveMatchItem {
    Long memberId;
    String nickname;
    List<CategoryDTO> categoryDTOList;
    Double latitude;
    Double longitude;
    String content;
    String place;
    Double distance;
}
