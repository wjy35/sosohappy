package com.ssafy.help.match.api.service.impl;

import com.ssafy.help.match.api.service.MemberPointManageService;
import com.ssafy.help.match.db.repository.MemberPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberPointManageServiceImpl implements MemberPointManageService {
    private final MemberPointRepository memberPointRepository;

    @Override
    public void save(Point point, Long memberId) {
        memberPointRepository.save(point,memberId);
    }

    @Override
    public void delete(Long memberId) {
        memberPointRepository.delete(memberId);
    }
}
