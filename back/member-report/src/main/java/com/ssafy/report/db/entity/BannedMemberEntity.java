package com.ssafy.report.db.entity;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "banned_member")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BannedMemberEntity {
    @Id
    @Column(name = "member_id")
    Long memberId;

    @Column(name = "end_timestamp")
    Timestamp endTimestamp;

    public void setEndTimestamp(Timestamp endTimestamp) {
        this.endTimestamp = endTimestamp;
    }
}
