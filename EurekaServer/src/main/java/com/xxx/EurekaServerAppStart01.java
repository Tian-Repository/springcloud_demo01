package com.xxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerAppStart01 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerAppStart01.class,args);
    }
}
