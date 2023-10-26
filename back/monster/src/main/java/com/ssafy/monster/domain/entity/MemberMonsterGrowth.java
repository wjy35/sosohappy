package com.ssafy.monster.domain.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMonsterGrowth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberMonsterId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberMonsterProfile memberMonsterProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private MonsterType monsterType;

    @NotNull
    @ColumnDefault("0")
    private int monsterClover;

    public void addMonsterClover(int clover){
        this.monsterClover += clover;
    }
}
