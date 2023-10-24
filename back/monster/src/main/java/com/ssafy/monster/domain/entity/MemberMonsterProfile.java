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

    public void updateClover(int memberClover){
        this.memberClover = memberClover;
    }

}
