package com.ssafy.help.match.db.repository;


import org.springframework.data.geo.Point;

public interface MemberPointRepository {
    void save(Point point,Long memberId);
    void delete(Long memberId);
}
