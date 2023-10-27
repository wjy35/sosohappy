package com.ssafy.member.db.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "member")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@DynamicInsert
@DynamicUpdate
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long memberId;

    @Column(name = "disabled")
    Boolean disabled;

    @Column(name = "gender")
    Integer gender;

    @Column(name = "createdAt")
    Timestamp createdAt;

    @Column(name = "name")
    String name;

    @Column(name = "nickname")
    String nickname;

    @Column(name = "profileMonsterId")
    Integer profileMonsterId;

    @Column(name = "profileBackgroundId")
    Integer profileBackgroundId;

    @Column(name = "memberSignId")
    String memberSignId;

    @Column(name = "memberSignPassword")
    String memberSignPassword;

    public void setMemberSignPassword(String memberSignPassword) {
        this.memberSignPassword = memberSignPassword;
    }
}
