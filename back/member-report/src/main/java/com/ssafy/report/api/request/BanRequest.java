package com.ssafy.report.api.request;

import lombok.Data;

@Data
public class BanRequest {
    Long bannedMemberId;
    Integer day;
}
