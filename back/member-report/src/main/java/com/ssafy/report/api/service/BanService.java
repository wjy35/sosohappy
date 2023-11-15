package com.ssafy.report.api.service;

import com.ssafy.report.db.entity.BannedMemberEntity;

import java.util.List;

public interface BanService {
    void ban(Long bannedMemberId,Integer day);
    List<BannedMemberEntity> getBannedMemberList();
    void cancel(Long memberId);
}
