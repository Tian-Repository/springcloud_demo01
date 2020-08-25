package com.xxx.service.impl;

import com.xxx.pojo.Usr;
import com.xxx.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//降级处理实现类
@Service
public class UserServiceImpl implements UserService {

    @Override
    public Map getUserList() {
        //降级处理时，返回空的用户列表为空，同时携带错误提示
        HashMap map = new HashMap();
        //前端需要接收一个叫list的参数
        map.put("list",new ArrayList());
        map.put("ProviderVersion","获取远程服务调用失败");
        return map;
    }

    @Override
    public void createUser(Usr user) {
        System.out.println("创建用户"+user+"失败");
    }

    @Override
    public Usr getUser(Long id) {
        System.out.println("获取id为："+id+"的用户失败");
        return null;
    }

    @Override
    public void updateUser(Long id, Usr user) {
        System.out.println("更新id为："+id+"的用户失败");
    }

    @Override
    public void deleteUser(Long id) {
        System.out.println("删除id为："+id+"的用户失败");
    }
}
