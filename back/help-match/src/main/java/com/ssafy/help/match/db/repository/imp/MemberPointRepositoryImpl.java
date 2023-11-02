package com.ssafy.help.match.db.repository.imp;

import com.ssafy.help.match.db.repository.MemberPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberPointRepositoryImpl implements MemberPointRepository {
    private final RedisTemplate<String,Long> redisTemplate;
    private final String KEY = "map";

    @Override
    public void save(Point point,Long memberId) {
        redisTemplate.opsForGeo().add(KEY,point,memberId);
    }

    @Override
    public void delete(Long memberId) {
        redisTemplate.opsForGeo().remove(KEY,memberId);
    }
}
