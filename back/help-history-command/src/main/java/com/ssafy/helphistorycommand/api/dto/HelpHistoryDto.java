package com.ssafy.helphistorycommand.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HelpHistoryDto {
    long toMemberId;
    long fromMemberId;
    long categoryId;
    String content;
    double latitude;
    double longitude;
}
