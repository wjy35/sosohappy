package com.ssafy.help.match.socket.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FortuneCookieItem {
    String fortuneCookieId;
    Long otherMemberId;
    String content;
    Timestamp timestamp;
    Integer categoryId;
}
