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
public class MemberMonsterProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @NotNull
    private int memberClover;

    @NotNull
    private Long memberAccruedClover;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monster_id")
    @ColumnDefault("1")
    private MonsterInfo monsterInfo;

    public void addMemberClover(int clover){
        this.memberClover += clover;
    }
    public void removeMemberClover(int clover){
        this.memberClover -= clover;
    }
    public void addMemberAccruedClover(int clover){
        this.memberAccruedClover += clover;
    }

    public void setMonsterInfo(MonsterInfo monsterInfo) {
        this.monsterInfo = monsterInfo;
    }

}
