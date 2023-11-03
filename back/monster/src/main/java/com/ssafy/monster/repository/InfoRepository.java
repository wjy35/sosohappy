package com.ssafy.monster.repository;

import com.ssafy.monster.domain.entity.MonsterInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InfoRepository extends JpaRepository<MonsterInfo, Integer> {

    Optional<MonsterInfo> findByMonsterId(int memberMonsterId);

    @Query(value = "SELECT SUM(required_clover) OVER(ORDER BY monster_level) AS required FROM monster_info WHERE type_id = 1", nativeQuery = true)
    List<Integer> getMonsterCloverInfo();

    @Query(value = "SELECT monster_level FROM monster_info WHERE type_id = 1", nativeQuery = true)
    List<Integer> getMonsterLevels();

}
