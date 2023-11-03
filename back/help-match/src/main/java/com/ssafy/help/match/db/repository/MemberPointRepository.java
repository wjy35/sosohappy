package com.ssafy.help.match.db.repository;


import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;

import java.util.List;

public interface MemberPointRepository {
    void save(Point point,Long memberId);
    void delete(Long memberId);

    GeoResults<RedisGeoCommands.GeoLocation<String>> search(Point point, double metric);
}
