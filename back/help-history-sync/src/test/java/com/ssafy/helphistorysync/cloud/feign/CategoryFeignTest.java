package com.ssafy.helphistorysync.cloud.feign;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")
public class CategoryFeignTest {

    @Autowired
    private CategoryFeign categoryFeign;

    /**
     * 읽기 전용이기 때문에 DB의 의존성을 지우지 않고 직접 통신. CUD 작업의 경우 지양
     */
    @DisplayName("CategoryFeign을 이용한 통신이 정상적인 결과(상태, 예상 출력값)를 가져오는지 체크한다.")
    @Test
    public void testGetCategoryDetail() throws Exception {

        //given
        long categoryId = 1; // 테스트할 카테고리 ID
        String responseJson = "{\"status\":\"success\",\"message\":\"카테고리 정보를 성공적으로 불러왔습니다.\"" +
                ",\"result\":{\"category\":{\"categoryId\":1,\"categoryName\":\"계단 이용\",\"categoryImage\":\"" +
                "<svg id=\\\"Capa_1\\\" enable-background=\\\"new 0 0 512 512\\\" viewBox=\\\"0 0 512 512\\\" " +
                "xmlns=\\\"http://www.w3.org/2000/svg\\\"><g>" +
                "<path d=\\\"m497 66h-47l-261.404 190.185 81.404 189.815h227c8.284 0 15-6.716 15-15v-350c0-8.284-6.716-15-15-15z\\\"" +
                " fill=\\\"#ffa07a\\\"/><g><g><path d=\\\"m450 142h-45l-103 16-122-16v-61c0-8.284 6.716-15 15-15h255z\\\" " +
                "fill=\\\"#ffe278\\\"/></g><g><path d=\\\"m405 218h-45l-127 16-98-16v-61c0-8.284 6.716-15 15-15h255z\\\" " +
                "fill=\\\"#ffc178\\\"/></g><g><path d=\\\"m360 294h-45l-113 16-112-16v-61c0-8.284 6.716-15 15-15h255z\\\" " +
                "fill=\\\"#ffe278\\\"/></g><g><path d=\\\"m315 370h-45l-105 16-120-16v-61c0-8.284 6.716-15 15-15h255z\\\" " +
                "fill=\\\"#ffc178\\\"/></g><g><path d=\\\"m270 446h-255c-8.284 0-15-6.716-15-15v-46c0-8.284 6.716-15 15-15h255z\\\"" +
                " fill=\\\"#ffe278\\\"/></g></g></g></svg>\"}}}";
        String message = "카테고리 정보를 성공적으로 불러왔습니다.";
        JSONObject json = new JSONObject(responseJson);

        // when
        String result = categoryFeign.getCategoryDetail(1);

        // then
        assertThat(json.get("message")).isEqualTo(message);
        assertThat(result).isEqualTo(responseJson);
    }

}