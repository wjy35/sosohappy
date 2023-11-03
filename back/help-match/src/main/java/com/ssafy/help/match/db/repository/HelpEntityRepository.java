package com.ssafy.help.match.db.repository;

import com.ssafy.help.match.db.entity.HelpEntity;

public interface HelpEntityRepository {
    void save(HelpEntity helpEntity);

    HelpEntity findByMemberId(Long memberId);

    boolean existByMemberId(Long memberId);
}
