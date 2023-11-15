package com.ssafy.report.api.service.impl;

import com.ssafy.report.api.service.AdminService;
import com.ssafy.report.db.repository.AdminEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminEntityRepository adminEntityRepository;

    @Override
    public boolean isAdmin(Long memberId) {
        return adminEntityRepository.existsAdminEntityByMemberId(memberId);
    }
}
