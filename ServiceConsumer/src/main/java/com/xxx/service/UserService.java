package com.xxx.service;


import com.xxx.pojo.Usr;

import java.util.List;
import java.util.Map;

public interface UserService {
    //获取全部用户,因为在前边的提供者的controller里返回的是map，此处也要map接
    Map getUserList();

    //新增用户
    void createUser(Usr user);

    //根据id获取用户信息
    Usr getUser(Long id);

    //根据id修改用户信息
    void updateUser(Long id, Usr user);

    //删除指定id的用户
    void deleteUser(Long id);

}
