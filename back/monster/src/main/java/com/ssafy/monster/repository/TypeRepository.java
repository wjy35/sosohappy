package com.ssafy.monster.repository;

import com.ssafy.monster.domain.entity.MonsterInfo;
import com.ssafy.monster.domain.entity.MonsterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<MonsterType, Byte> {
    List<MonsterType> findAll();

    MonsterType findByTypeId(Byte typeId);
}
