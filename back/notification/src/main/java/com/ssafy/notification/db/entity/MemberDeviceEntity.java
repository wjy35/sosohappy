package com.ssafy.notification.db.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member_device")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberDeviceEntity {
    @Id
    @Column(name = "member_id")
    Long memberId;

    @Column(name = "device_token")
    String deviceToken;
}
