package com.xxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerAppStart02 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerAppStart02.class,args);
    }
}
