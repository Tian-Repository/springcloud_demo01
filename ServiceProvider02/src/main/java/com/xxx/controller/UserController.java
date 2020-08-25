package com.xxx.controller;

import com.xxx.pojo.Usr;
import com.xxx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


@RestController
@RequestMapping("/user")//映射给到类上更规范，不易出错，如：用swagger2不给就容易出问题
public class UserController {
    @Autowired
    UserService userService;

    //获取全部用户信息
    @GetMapping("/getAll")
    public Map<String,Object> getUsers(){
        Map<String, Object> map = new HashMap<>();
        List<Usr> userList = userService.getUserList();
        map.put("list",userList);
        //用于标识服务
        String ProviderVersion="MyProvider002:0.01V";
        map.put("ProviderVersion",ProviderVersion);

        //此处让线程睡眠。帮助Consumer03模拟网络阻塞，展示熔断机制
        try {
            //熔断器默认熔断时间为1000毫秒，超过1000他会认为你这个服务出了问题，熔断此处，降级处理
            int i = new Random().nextInt(1200);//随机睡眠时间
            System.out.println("Provider02睡眠时间"+i);
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return map;
    }
    //保存新增用户接口
    @PostMapping("/save")
    public String createUser(@RequestBody Usr user){//将数据以json格式传入
        try {
            userService.createUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    //获取指定id用户信息
    @GetMapping("/get/{id}")
    public Usr findUser(@PathVariable Long id){
       /* Usr user = null;
        try {

            System.out.println("success");
            return user;
        } catch (Exception e) {
            System.out.println("error");
            return user;
        }*/
        System.out.println(id);
       return userService.getUser(id);
    }
    //修改指定id用户的信息
    @PutMapping("/update/{id}")
    public String editUser(@RequestBody Usr user,@PathVariable("id") Long id){
        try {
            userService.updateUser(user.getId(),user );
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
    //根据指定id删除用户
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        try {
            userService.deleteUser(id);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
    //获取服务名称
    @GetMapping("/getVersion")
    public String getVersion(){
        return "MyProvider002:0.01V";
    }
}
