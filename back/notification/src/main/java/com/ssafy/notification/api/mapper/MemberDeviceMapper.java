package com.ssafy.notification.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MemberDeviceMapper {
    MemberDeviceMapper INSTANCE = Mappers.getMapper(MemberDeviceMapper.class);


}
