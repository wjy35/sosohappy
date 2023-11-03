package com.ssafy.help.match.db.entity;

import com.ssafy.help.match.socket.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendMatchEntity {
    Long memberId;
    String nickname;
    List<CategoryDTO> categoryDTOList;
    Double latitude;
    Double longitude;
    String content;
    String place;
}
