package com.xxx.service.impl;

import com.xxx.dao.UserDao;
import com.xxx.pojo.Usr;
import com.xxx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired//自动注入UserDao
    UserDao userDao;

    //直接用jpa自动推断提供的方法

    @Override
    public List<Usr> getUserList() {
        return userDao.findAll();
    }

    @Override
    public void createUser(Usr user) {
        userDao.save(user);
    }

    @Override
    public Usr getUser(Long id) {
        System.out.println("proIMPL"+id);
        //返回值是一个option类型，调用get方法拿到值
        Usr usr =userDao.findById(id).get();
        System.out.println(usr);
        return usr;
    }

    @Override
    public void updateUser(Long id, Usr user) {
        user.setId(id);
        userDao.saveAndFlush(user);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }
}
