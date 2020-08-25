package com.xxx.dao;

import com.xxx.pojo.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

//继承JpaRepository将要操作的实体类和主键类型传入
public interface UserDao extends JpaRepository<Usr,Long> {
}
