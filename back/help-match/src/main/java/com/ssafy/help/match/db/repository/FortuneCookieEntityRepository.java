package com.ssafy.help.match.db.repository;

import com.ssafy.help.match.db.entity.FortuneCookieEntity;

import java.util.List;

public interface FortuneCookieEntityRepository {
    void save(Long memberId, FortuneCookieEntity fortuneCookieEntity);
    List<FortuneCookieEntity> getListByMemberId(Long memberId);
    void delete(Long memberId,String fortuneCookieId);
}
