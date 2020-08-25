package com.xxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ServiceConsumer03AppStart {
    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumer03AppStart.class,args);
    }

    @Bean
    @LoadBalanced//开启Ribbon
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
