package com.xxx;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
public class ServiceConsumer04AppStart {
    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumer04AppStart.class,args);
    }

    @Bean
    @LoadBalanced//开启Ribbon
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean//监控客户端中进行注册的servletBean
    public ServletRegistrationBean getServlet(){
        //一个用于发送请求的servlet对象
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean bean = new ServletRegistrationBean(streamServlet);
        bean.setLoadOnStartup(1);//设置启动时加载优先级
        //添加访问映射,也就是在熔断服务器页面访问的映射地址，
        bean.addUrlMappings("/hystrix.stream");
        bean.setName("HystrixMetricsStreamServlet");//设置名称
        return bean;
    }

}
