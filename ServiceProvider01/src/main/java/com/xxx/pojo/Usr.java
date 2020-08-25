package com.xxx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity//标记为实体类、自动生成表
@Data//自动生成getter、setter
@NoArgsConstructor//有参构造
@AllArgsConstructor//无参构造
public class Usr{

    //主键
    @Id
    @GeneratedValue
    private Long id;
    //列名叫name，允许为null，长度为30
    @Column(name = "name",nullable = true,length = 30)
    private String name;
    @Column(name = "age",nullable = true,length = 4)
    private Integer age;
}
