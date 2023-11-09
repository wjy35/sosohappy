package com.ssafy.help.match.api.service.impl;

import com.ssafy.help.match.api.service.MemberPointManageService;
import com.ssafy.help.match.db.entity.HelpEntity;
import com.ssafy.help.match.db.entity.HelpMatchStatus;
import com.ssafy.help.match.db.repository.HelpEntityRepository;
import com.ssafy.help.match.db.repository.MemberPointRepository;
import com.ssafy.help.match.db.repository.MemberSessionEntityRepository;
import com.ssafy.help.match.event.dto.PointChangeEventDTO;
import com.ssafy.help.match.event.emitter.EventEmitter;
import com.ssafy.help.match.event.emitter.EventTopicPrefix;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberPointManageServiceImpl implements MemberPointManageService {
    private final MemberPointRepository memberPointRepository;
    private final MemberSessionEntityRepository memberSessionEntityRepository;
    private final EventEmitter eventEmitter;

    @Override
    public void save(Point point, Long memberId, Long otherMemberId) {
        memberPointRepository.save(point,memberId);

        if(otherMemberId == null) return;
        if(!memberSessionEntityRepository.isOnMove(memberId)) return;
        if(!memberSessionEntityRepository.isConnected(otherMemberId)) return;

        PointChangeEventDTO pointChangeEvent =PointChangeEventDTO
                .builder()
                .receiveMemberId(otherMemberId)
                .longitude(point.getY())
                .latitude(point.getX())
                .build();

        eventEmitter.emit(EventTopicPrefix.POINT_CHANGE,pointChangeEvent);
    }

    @Override
    public void delete(Long memberId) {
        memberPointRepository.delete(memberId);
    }
}
