package com.ssafy.monster.domain.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MonsterInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int monsterId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private MonsterType monsterType;

    @NotNull
    @Column(nullable = false)
    private int requiredClover;

    @NotNull
    private int monsterLevel;
}
