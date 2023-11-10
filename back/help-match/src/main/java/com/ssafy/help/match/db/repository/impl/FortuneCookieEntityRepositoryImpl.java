package com.ssafy.help.match.db.repository.impl;

import com.ssafy.help.match.db.entity.FortuneCookieEntity;
import com.ssafy.help.match.db.repository.FortuneCookieEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class FortuneCookieEntityRepositoryImpl implements FortuneCookieEntityRepository {
    private final HashOperations<String,String,FortuneCookieEntity> fortuneCookieEntityHashOperations;
    private final String PREFIX="fortuneCookie:";

    @Override
    public void save(Long memberId, FortuneCookieEntity fortuneCookieEntity) {
        String uuid = UUID.randomUUID().toString();
        fortuneCookieEntity.setFortuneCookieId(uuid);

        fortuneCookieEntityHashOperations.put(PREFIX+memberId,uuid,fortuneCookieEntity);
    }

    @Override
    public List<FortuneCookieEntity> getListByMemberId(Long memberId) {
        return fortuneCookieEntityHashOperations.values(PREFIX+memberId);
    }

    @Override
    public void delete(Long memberId, String fortuneCookieId) {
        fortuneCookieEntityHashOperations.delete(PREFIX+memberId,fortuneCookieId);
    }
}
