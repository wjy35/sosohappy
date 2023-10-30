package com.ssafy.helphistorycommand.db.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "help_history")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HelpHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long historyId;

    @Column(name = "to_member_id", nullable = false)
    long toMemberId;

    @Column(name = "from_member_id", nullable = false)
    long fromMemberId;

    @Column(name = "category_id", nullable = false)
    long categoryId;

    @Column(name = "content", nullable = false)
    String content;

    @Column(name = "x", columnDefinition = "decimal(18,10)", nullable = false)
    long x;

    @Column(name = "y", columnDefinition = "decimal(18,10)", nullable = false)
    long y;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false)
    Timestamp createdAt;
}
