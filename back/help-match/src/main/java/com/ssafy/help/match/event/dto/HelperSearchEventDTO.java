package com.ssafy.help.match.event.dto;

import lombok.Data;

@Data
public class HelperSearchEventDTO implements EventDTO{
    Long memberId;
    Integer metric;
}
