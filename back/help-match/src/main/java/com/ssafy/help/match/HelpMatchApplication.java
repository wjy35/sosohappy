package com.ssafy.help.match;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HelpMatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelpMatchApplication.class, args);
    }

}
