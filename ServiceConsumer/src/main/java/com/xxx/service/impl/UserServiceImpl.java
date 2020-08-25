package com.xxx.service.impl;

import com.xxx.pojo.Usr;
import com.xxx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    //远程服务调用客户端
    @Autowired//此处虽然让自动注入但未注入进去，需要在启动类中创建restTemplate对象
    RestTemplate restTemplate;
    //Eureka客户端
    @Autowired//注意是org.springframework.cloud.client.discovery.DiscoveryClient;
    private DiscoveryClient discoveryClient;

    //通过客户端负载均衡器获取生产者服务器基础地址
    public String getServerUrl(){
        //通过客户端调用器查找指定服务,参数为服务提供者配置的应用名（在服务提供者配置文件中yml）
        List<ServiceInstance> insList = discoveryClient.getInstances("MyProvider01");
        //获取第一个服务器
        ServiceInstance instance = insList.get(0);
        String ip=instance.getHost();
        int port=instance.getPort();
        //最终目的来了，获取到服务提供者的调用地址
        String url = "http://"+ip+":"+port+"/user";//加/user是因为我们写提供者时映射地址都在/user下
        return url;
    }

    //借助restTemplate调用远程方法，url都要拼接你在服务提供者给的方法映射
    @Override
    public Map getUserList() {
        //getForObject获取,参数分别为url：访问地址，远程调用方法的返回值的class属性
        return restTemplate.getForObject(getServerUrl()+"/getAll", Map.class);
    }

    @Override
    public void createUser(Usr user) {
        //postForObject增加
        restTemplate.postForObject(getServerUrl()+"/save",user ,String.class );
    }

    @Override
    public Usr getUser(Long id) {
        return restTemplate.getForObject(getServerUrl()+"/get/"+id,Usr.class );
    }

    @Override
    public void updateUser(Long id, Usr user) {
        restTemplate.put(getServerUrl()+"/update/"+id,user);
    }

    @Override
    public void deleteUser(Long id) {
        restTemplate.delete(getServerUrl()+"/delete/"+id);
    }
}
