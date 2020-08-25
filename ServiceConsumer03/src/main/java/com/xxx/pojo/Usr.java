package com.xxx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data//自动生成getter、setter
@NoArgsConstructor//有参构造
@AllArgsConstructor//无参构造
public class Usr {

    private Long id;

    private String name;

    private Integer age;
}
