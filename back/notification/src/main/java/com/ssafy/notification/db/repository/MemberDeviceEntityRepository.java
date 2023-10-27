package com.ssafy.notification.db.repository;

import com.ssafy.notification.db.entity.MemberDeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDeviceEntityRepository extends JpaRepository<MemberDeviceEntity,Long> {
    MemberDeviceEntity save(MemberDeviceEntity memberDeviceEntity);
    MemberDeviceEntity findByMemberId(Long memberId);
}
