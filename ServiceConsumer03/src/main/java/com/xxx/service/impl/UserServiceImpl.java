package com.xxx.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.xxx.pojo.Usr;
import com.xxx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    //远程服务调用客户端
    @Autowired//此处虽然让自动注入但未注入进去，需要在启动类中创建restTemplate对象
    RestTemplate restTemplate;

    //定义远程访问地址: http://服务提供者的应用名/映射名（此处加映射是因为我的服务全是访问/user下的方法）
    String url = "http://MyProvider/user";

    //借助restTemplate调用远程方法，url都要拼接你在服务提供者给的方法映射

    //参数fallbackMethod表示熔断机制触发后要调用的方法
    @HystrixCommand(fallbackMethod="getAllFallBackMethod")
    public Map getUserList() {
        long startTime = System.currentTimeMillis();//调用前
        //getForObject获取,参数分别为url：访问地址，远程调用方法的返回值的class属性
        Map map = restTemplate.getForObject(url + "/getAll", Map.class);
        long end = System.currentTimeMillis();//调用后
        System.out.println("调用远程服务用时为："+(startTime-end));
        return map;
    }

    //此方法作为getUserList()方法的降级处理方法，即触发熔断机制后要调用的方法
    public Map getAllFallBackMethod(){
        //因为getUserList()返回的是map用给前端拿数据，此处降级处理方法也返回一个map，携带信息
        Map map = new HashMap();
        //正常情况下返回的是查询到的用户信息列表，现在出错，返回一个空列表
        map.put("list",new ArrayList());
        //正常返回一个在服务提供者中自定义的变量用于页面标识是哪个提供者在提供服务，此处返回错误信息
        map.put("ProviderVersion","远程服务调用失败");
        return map;
    }

    @Override
    public void createUser(Usr user) {
        //postForObject增加
        restTemplate.postForObject(url+"/save",user ,String.class );
    }

    @Override
    public Usr getUser(Long id) {
        return restTemplate.getForObject(url+"/get/"+id,Usr.class );
    }

    @Override
    public void updateUser(Long id, Usr user) {
        restTemplate.put(url+"/update/"+id,user);
    }

    @Override
    public void deleteUser(Long id) {
        restTemplate.delete(url+"/delete/"+id);
    }
}
