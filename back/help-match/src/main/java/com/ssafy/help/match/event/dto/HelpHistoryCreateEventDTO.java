package com.ssafy.help.match.event.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HelpHistoryCreateEventDTO implements EventDTO{
    Long toMemberId;
    Long fromMemberId;
    Integer categoryId;
    String content;
    Double latitude;
    Double longitude;
}
