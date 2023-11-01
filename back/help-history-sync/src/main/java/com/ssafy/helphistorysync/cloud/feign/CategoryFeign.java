package com.ssafy.helphistorysync.cloud.feign;

import com.ssafy.helphistorysync.api.request.CategoryRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "category",url = "https://sosohappy.co.kr/category")
public interface CategoryFeign {

    @GetMapping("/getCategoryInfo/{categoryId}")
    String getCategoryDetail( @PathVariable long categoryId);
}
