package com.xxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient//服务发现
public class ServiceProvider02AppStart {
    public static void main(String[] args) {
        SpringApplication.run(ServiceProvider02AppStart.class,args);
    }
}
