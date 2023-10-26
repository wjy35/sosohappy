package com.ssafy.helphistoryquery.db.entity;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryEntity{
    private long categoryId;
    private String categoryName;
    private String categoryImage;

}
