package com.ssafy.report.api.service.impl;

import com.ssafy.report.api.response.ReportedMemberResponse;
import com.ssafy.report.api.service.ReportService;
import com.ssafy.report.cloud.openfeign.MemberOpenFeign;
import com.ssafy.report.db.entity.MemberReportEntity;
import com.ssafy.report.db.repository.MemberReportEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final MemberReportEntityRepository memberReportEntityRepository;
    private final MemberOpenFeign memberOpenFeign;

    @Override
    public void reportMember(MemberReportEntity memberReportEntity) {
        memberReportEntityRepository.save(memberReportEntity);
    }

    @Override
    public List<ReportedMemberResponse> getReportedMemberList() {
        return memberReportEntityRepository.findAll()
                .stream()
                .map((memberReportEntity)-> ReportedMemberResponse.builder()
                        .reportedMemberId(memberReportEntity.getReportedMemberId())
                        .reportedNickname(memberOpenFeign.getMemberDto(memberReportEntity.getReportedMemberId()).getNickname())
                        .reportingNickname(memberOpenFeign.getMemberDto(memberReportEntity.getReportingMemberId()).getNickname())
                        .build())
                .collect(Collectors.toList());
    }
}
