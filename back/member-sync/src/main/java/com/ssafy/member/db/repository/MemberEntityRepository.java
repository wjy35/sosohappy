package com.ssafy.member.db.repository;

import com.ssafy.member.db.entity.MemberEntity;

import java.util.Map;

public interface MemberEntityRepository {
    void save(MemberEntity memberEntity);
    void update(MemberEntity before,MemberEntity after);
}

