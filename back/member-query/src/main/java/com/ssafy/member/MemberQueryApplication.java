package com.ssafy.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MemberQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberQueryApplication.class, args);
    }

}
