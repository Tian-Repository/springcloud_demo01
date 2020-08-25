package com.xxx.service;


import com.xxx.config.FeignConfig;
import com.xxx.pojo.Usr;
import com.xxx.service.impl.UserServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//value参数为服务提供者的应用名,configuration参数为自定义Feign配置类的class属性,fallback为熔断后调用的降级处理方法的class属性
@FeignClient(value = "MyProvider",configuration = FeignConfig.class,fallback = UserServiceImpl.class)
public interface UserService {
    //获取全部用户,因为在前边的提供者的controller里返回的是map，此处也要map接
    @GetMapping("/user/getAll")//参数为服务提供者controller方法访问的路径
    Map getUserList();

    //新增用户
    @PostMapping("/user/save")
    void createUser(Usr user);

    //根据id获取用户信息
    @GetMapping("/user/get/{id}")
    Usr getUser(@PathVariable("id") Long id);

    //根据id修改用户信息
    @PutMapping("/user/update/{id}")
    void updateUser(@PathVariable("id") Long id, @RequestBody Usr user);

    //删除指定id的用户
    @DeleteMapping("/user/delete/{id}")
    void deleteUser(@PathVariable("id") Long id);

}
