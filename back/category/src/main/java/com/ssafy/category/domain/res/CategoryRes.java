package com.ssafy.category.domain.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryRes {

    Long categoryId;
    String categoryName;
    String categoryImage;

}
