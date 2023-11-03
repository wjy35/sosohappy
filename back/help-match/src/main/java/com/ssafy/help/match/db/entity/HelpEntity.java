package com.ssafy.help.match.db.entity;

import com.ssafy.help.match.socket.request.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HelpEntity {
    Long memberId;
    String nickname;
    List<Category> categoryList;
    Double latitude;
    Double longitude;
    String content;
    String place;
}
