package com.ssafy.apigateway;

import com.ssafy.apigateway.util.JwtUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootTest
@EnableEurekaClient
class ApiGatewayApplicationTests {

    @Autowired
    JwtUtil jwtUtil;
    @Value("${jwt.salt}")
    private String SALT;

    @Test
    void contextLoads() {
        Assertions.assertThat(jwtUtil).isNotNull();
    }

    @Test
    void testGenerateToken(){
        // given
        Long memberId = 1l;
        System.out.println("memberId = " + memberId);

        // when
        String jwtToken = jwtUtil.generateToken(memberId);

        // then
        System.out.println("jwtToken = " + jwtToken);
        Assertions.assertThat(jwtToken).isNotNull();
    }

    @Test
    void testGetMemberId(){
        // given
        Long encodeMemberId = 1l;
        System.out.println("encodeMemberId = " + encodeMemberId);

        String jwtToken = jwtUtil.generateToken(encodeMemberId);
        System.out.println("jwtToken = " + jwtToken);

        // when
        Long decodeMemberId = jwtUtil.getMemberIdByToken(jwtToken);

        // then
        System.out.println("decodeMemberId = " + decodeMemberId);
        Assertions.assertThat(decodeMemberId).isEqualTo(encodeMemberId);
    }

}
