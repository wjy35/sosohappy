package com.ssafy.helphistorysync.db.repository;

import com.ssafy.helphistorysync.db.entity.HelpHistoryEntity;

public interface HelpHistoryRepository {
    void addHelpHistory(HelpHistoryEntity helpHistoryEntity);
}
