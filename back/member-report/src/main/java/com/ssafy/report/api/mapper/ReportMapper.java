package com.ssafy.report.api.mapper;

import com.ssafy.report.api.request.ReportRequest;
import com.ssafy.report.db.entity.MemberReportEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReportMapper {
    ReportMapper INSTANCE = Mappers.getMapper(ReportMapper.class);

    MemberReportEntity toEntity(ReportRequest request);
}
