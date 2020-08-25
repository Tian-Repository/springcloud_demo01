package com.xxx.service;


import com.xxx.pojo.Usr;

import java.util.List;

public interface UserService {
    //获取全部用户
    List<Usr> getUserList();

    //新增用户
    void createUser(Usr user);

    //根据id获取用户信息
    Usr getUser(Long id);

    //根据id修改用户信息
    void updateUser(Long id, Usr user);

    //删除指定id的用户
    void deleteUser(Long id);

}
