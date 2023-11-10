package com.ssafy.help.match.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FortuneCookieEntity {
    String fortuneCookieId;
    Long otherMemberId;
    String content;

    @Builder.Default
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
}
