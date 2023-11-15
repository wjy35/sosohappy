package com.ssafy.observer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HelpMatchObserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelpMatchObserverApplication.class, args);
    }

}
