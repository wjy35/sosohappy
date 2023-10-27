package com.ssafy.notification.api.mapper;

import com.ssafy.notification.api.request.MemberDeviceSaveRequest;
import com.ssafy.notification.db.entity.MemberDeviceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberDeviceMapper {
    MemberDeviceMapper INSTANCE = Mappers.getMapper(MemberDeviceMapper.class);

    MemberDeviceEntity toEntity(Long memberId, String deviceToken);
}
