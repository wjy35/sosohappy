package com.ssafy.helphistorycommand.db.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "fortune_card")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FortuneCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    long fortuneCardId;

    @Column(name = "content", nullable = false)
    String content;
}
