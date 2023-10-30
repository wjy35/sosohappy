package com.ssafy.helphistorycommand.db.repository;

import com.ssafy.helphistorycommand.db.entity.HelpHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelpHistoryRepository extends JpaRepository<HelpHistoryEntity, Long> {

}
