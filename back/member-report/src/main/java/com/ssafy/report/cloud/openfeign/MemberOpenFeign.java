package com.ssafy.report.cloud.openfeign;

import com.ssafy.report.cloud.response.MemberResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value="notification-member", url = "https://sosohappy.co.kr/member-query")
public interface MemberOpenFeign {
    @GetMapping("/public/{memberId}")
    MemberResponse getMemberDto(@PathVariable Long memberId);
}

