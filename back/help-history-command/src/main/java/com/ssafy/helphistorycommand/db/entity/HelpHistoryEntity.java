package com.ssafy.helphistorycommand.db.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "helphistory")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HelpHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long historyId;

    @Column(name = "to_member_id")
    long toMemberId;

    @Column(name = "from_member_id")
    long fromMemberId;

    @Column(name = "category_id")
    long categoryId;

    @Column(name = "content")
    String content;

    @Column(name = "x")
    long x;

    @Column(name = "y")
    long y;

    @Column(name = "created_at")
    Timestamp createdAt;
}
