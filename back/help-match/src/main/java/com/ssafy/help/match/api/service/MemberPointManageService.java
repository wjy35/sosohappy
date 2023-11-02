package com.ssafy.help.match.api.service;

import org.springframework.data.geo.Point;

public interface MemberPointManageService {
    void save(Point point, Long memberId);
    void delete(Long memberId);
}
