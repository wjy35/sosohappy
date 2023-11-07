package com.ssafy.help.match.db.repository;

import com.ssafy.help.match.db.entity.HelpEntity;

public interface HelpEntityRepository {
    void save(Long memberId,HelpEntity helpEntity);
    HelpEntity getAndDeleteByMemberId(Long memberId);
    HelpEntity findByMemberId(Long memberId);
}
