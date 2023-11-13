package com.ssafy.observer.event.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StatusChangeEventDTO implements EventDTO{
    Long memberId;
}
