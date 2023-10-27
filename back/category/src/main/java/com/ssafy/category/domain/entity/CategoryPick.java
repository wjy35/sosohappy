package com.ssafy.category.domain.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryPick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pickId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull
    private Long memberId;

    @NotNull
    private int pickCount;

    @UpdateTimestamp
    private Timestamp pickTime;
}