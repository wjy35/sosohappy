package com.ssafy.helphistorycommand.api.request;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HelpHistoryRequest {
    private long toMemberId;
    private long fromMemberId;
    private long categoryId;
    private String content;
    private String x;
    private String y;
}
