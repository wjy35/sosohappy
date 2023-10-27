package com.ssafy.notification.api.request;

import lombok.Data;

@Data
public class MemberDeviceSaveRequest {
    Long memberId;
    String deviceToken;
}
