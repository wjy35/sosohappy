package com.ssafy.observer.event.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchPopEventDTO implements EventDTO{
    Long memberId;
    Long matchedMemberId;
}
