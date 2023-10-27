package com.ssafy.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MemberCommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberCommandApplication.class, args);
    }

}
