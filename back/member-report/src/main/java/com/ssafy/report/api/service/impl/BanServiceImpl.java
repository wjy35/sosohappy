package com.ssafy.report.api.service.impl;

import com.ssafy.report.api.service.BanService;
import com.ssafy.report.db.entity.BannedMemberEntity;
import com.ssafy.report.db.repository.BannedMemberEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BanServiceImpl implements BanService {
    private final BannedMemberEntityRepository bannedMemberEntityRepository;

    @Override
    public void ban(Long bannedMemberId, Integer day) {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        BannedMemberEntity bannedMemberEntity = bannedMemberEntityRepository.findById(bannedMemberId).orElseGet(
                ()->BannedMemberEntity.builder()
                        .memberId(bannedMemberId)
                        .endTimestamp(now)
                        .build());

        if(bannedMemberEntity.getEndTimestamp().before(now)) bannedMemberEntity.setEndTimestamp(now);

        bannedMemberEntityRepository.save(bannedMemberEntity);
    }

    @Override
    public List<BannedMemberEntity> getBannedMemberList() {
        return bannedMemberEntityRepository.findAll();
    }

    @Override
    public void cancel(Long memberId) {
        bannedMemberEntityRepository.deleteById(memberId);
    }
}
