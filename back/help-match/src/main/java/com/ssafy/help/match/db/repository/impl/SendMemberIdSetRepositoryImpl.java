package com.ssafy.help.match.db.repository.impl;

import com.ssafy.help.match.db.repository.SendMemberIdSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@RequiredArgsConstructor
public class SendMemberIdSetRepositoryImpl implements SendMemberIdSetRepository {
    private final SetOperations<String,Long> setOperations;
    private final String PREFIX="sendMemberIdSet:";

    @Override
    public void save(Long memberId, Long sendMemberId) {
        setOperations.add(PREFIX+memberId,sendMemberId);
    }

    @Override
    public void delete(Long memberId, Long sendMemberId) {
        setOperations.remove(PREFIX+memberId,sendMemberId);
    }

    @Override
    public Set<Long> getSet(Long memberId) {
        return setOperations.members(PREFIX+memberId);
    }
}
