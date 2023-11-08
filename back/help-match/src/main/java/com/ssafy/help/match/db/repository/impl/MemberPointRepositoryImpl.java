package com.ssafy.help.match.db.repository.impl;

import com.ssafy.help.match.db.repository.MemberPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberPointRepositoryImpl implements MemberPointRepository {
    private final RedisTemplate<String,String> redisTemplate;
    private final String KEY = "map";

    @Override
    public void save(Point point,Long memberId) {
        redisTemplate.opsForGeo().add(KEY,point,memberId.toString());
    }

    @Override
    public void delete(Long memberId) {
        redisTemplate.opsForGeo().remove(KEY,memberId.toString());
    }

    @Override
    public GeoResults<RedisGeoCommands.GeoLocation<String>> search(Point point, double metric) {
        return redisTemplate.opsForGeo().search(KEY,new Circle(point,metric));
    }

    @Override
    public Double getDistance(Long memberId1,Long memberId2) {
        return redisTemplate.opsForGeo().distance(KEY,memberId1.toString(),memberId2.toString()).getValue();
    }

    @Override
    public Point find(Long memberId) {
        List<Point> pointList = Optional.ofNullable(redisTemplate.opsForGeo().position(KEY,memberId.toString()))
                .orElseThrow();
        if(pointList.size()==1) return pointList.get(0);
        throw new RuntimeException();
    }
}
