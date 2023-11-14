package com.ssafy.helphistoryquery.db.repository;

import com.ssafy.helphistoryquery.db.entity.HelpCertificateEntity;
import com.ssafy.helphistoryquery.db.entity.HelpHistoryEntity;

import java.util.List;

public interface HelpHistoryRepository {
    Integer getHelpCount(Long memberId);

    List<HelpHistoryEntity> getHelpReceivedList(Long memberId);

    List<HelpHistoryEntity> getHelpHistoryEntityList(List<Object> objectList);

    HelpHistoryEntity parseJsonToEntity(Object json);

    List<HelpCertificateEntity> getHelpCertificateList(Long memberId);

    List<HelpCertificateEntity> getHelpCertificateEntityList(List<Object> objectList);

    HelpCertificateEntity parseJsonToHelpCertificateEntity(Object json);
}
