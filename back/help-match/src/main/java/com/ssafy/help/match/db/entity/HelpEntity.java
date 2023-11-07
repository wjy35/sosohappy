package com.ssafy.help.match.db.entity;

import com.ssafy.help.match.socket.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HelpEntity {
    Long otherMemberId;
    CategoryDTO category;
    String content;
    String place;
}
