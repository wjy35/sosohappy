package com.ssafy.help.match.event.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointChangeEventDTO implements EventDTO{
    Long receiveMemberId;
    Double longitude;
    Double latitude;
}
