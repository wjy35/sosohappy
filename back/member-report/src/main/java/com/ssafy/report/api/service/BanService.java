package com.ssafy.report.api.service;

import com.ssafy.report.api.response.BannedMemberParam;
import java.util.List;

public interface BanService {
    void ban(Long bannedMemberId,Integer day);
    List<BannedMemberParam> getBannedMemberList();
    void cancel(Long memberId);
}
