package com.ssafy.helphistorysync.db.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.helphistorysync.db.entity.HelpCertificateEntity;
import com.ssafy.helphistorysync.db.entity.HelpHistoryEntity;

public interface HelpHistoryRepository {
    void addHelpHistory(HelpHistoryEntity helpHistoryEntity) throws JsonProcessingException;

    void addHelpCertificate(HelpCertificateEntity helpCertificateEntity, long memberId) throws JsonProcessingException;
}
