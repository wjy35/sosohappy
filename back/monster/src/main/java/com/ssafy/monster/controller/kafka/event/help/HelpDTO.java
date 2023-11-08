package com.ssafy.monster.controller.kafka.event.help;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HelpDTO {
    public Long historyId;
    public Long categoryId;
    public String content;
    public Timestamp createdAt;
    public Long fromMemberId;
    public Long toMemberId;
    public double x;
    public double y;
}
