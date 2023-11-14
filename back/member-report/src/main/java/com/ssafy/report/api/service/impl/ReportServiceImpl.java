package com.ssafy.report.api.service.impl;

import com.ssafy.report.api.service.ReportService;
import com.ssafy.report.db.entity.MemberReportEntity;
import com.ssafy.report.db.repository.MemberReportEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final MemberReportEntityRepository memberReportEntityRepository;

    @Override
    public void reportMember(MemberReportEntity memberReportEntity) {
        memberReportEntityRepository.save(memberReportEntity);
    }

    @Override
    public List<MemberReportEntity> getReportedMemberList() {
        return memberReportEntityRepository.findAll();
    }
}
