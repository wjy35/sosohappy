package com.ssafy.helphistorycommand.cloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "member-query",url = "https://sosohappy.co.kr/member-query")
public interface MemberFeign {
    @GetMapping("/public/{memberId}")
    String getMemberDetail(@PathVariable long memberId);
}
