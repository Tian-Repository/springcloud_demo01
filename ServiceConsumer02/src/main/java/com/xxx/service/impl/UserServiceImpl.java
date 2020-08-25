package com.xxx.service.impl;

import com.xxx.pojo.Usr;
import com.xxx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    //远程服务调用客户端
    @Autowired//此处虽然让自动注入但未注入进去，需要在启动类中创建restTemplate对象
    RestTemplate restTemplate;
    @Autowired//使用LoadBalancerClient
    LoadBalancerClient loadBalancerClient;

    //通过客户端负载均衡器获取生产者服务器基础地址
    public String getServerUrl(){
        //获取服务实例，参数为服务的应用名，也就是provider里yml配置文件中的application.name,
        // 也可以将这个名字全部大写，因为你会发现在注册中心显示时，显示的是应用名全大写
        ServiceInstance instance = loadBalancerClient.choose("MyProvider");

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
