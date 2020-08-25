package com.xxx.controller;

import com.xxx.pojo.Usr;
import com.xxx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ConsumerController {

    @Autowired
    UserService userService;
    //获取全部用户的信息
    @GetMapping("/")
    public String getUserList(Model model){
        Map map = userService.getUserList();
        //服务提供者中存储了list
        List<Usr> list= (List<Usr>) map.get("list");
        //前端页面thymeleaf中获取page
        model.addAttribute("page",list);
        model.addAttribute("ProviderVersion",map.get("ProviderVersion"));
        //前端页面在resources下的template/user/list.html
        return "user/list";
    }
    //跳转到新增用户界面,页面就叫user/userAdd.html
    @RequestMapping("/toAdd")
    public String toAdd(Usr user){
        return "user/userAdd";
    }
    //新增用户实现
    @PostMapping("/add")
    public String createUser(Usr user){
        System.out.println(1);
        userService.createUser(user);
        //添加完用户跳转到查询所有用户页面，可以看到新增的用户
        return "redirect:/";
    }
    //跳转到编辑用户信息界面，并且将原来的用户信息显示
    @RequestMapping("/toEdit/{id}")
    public String toEdit(Model model, @PathVariable("id") Long id){
        System.out.println(id);
        Usr user = userService.getUser(id);
        System.out.println(user);
        //携带原来的用户信息
        model.addAttribute("user",user);
        return "user/userEdit";//跳转到userEdit.html页面
    }
    @RequestMapping("/edit")
    public String edit(Usr user){
        userService.updateUser(user.getId(),user);
        return "redirect:/";//跳转到查询所有用户界面，看是否修改成功了
    }
    //删除指定id用户
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/";
    }
}
