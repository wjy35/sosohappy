package com.ssafy.notification.api.service.impl;

import com.ssafy.notification.api.service.MemberDeviceManageService;
import com.ssafy.notification.db.entity.MemberDeviceEntity;
import com.ssafy.notification.db.repository.MemberDeviceEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDeviceManageServiceImpl implements MemberDeviceManageService {
    private final MemberDeviceEntityRepository memberDeviceEntityRepository;

    @Override
    public void save(MemberDeviceEntity memberDeviceEntity) {
        memberDeviceEntityRepository.save(memberDeviceEntity);
    }
}
