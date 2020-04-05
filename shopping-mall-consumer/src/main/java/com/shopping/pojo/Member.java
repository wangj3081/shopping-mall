package com.shopping.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Member
 * @Description 存储的redis对象实体类
 * @Author wangjian
 * @Date 2020/4/5 4:50 下午
 * @Version 1.0
 **/
@Data
public class Member implements Serializable {

    private String name;

    private Integer age;

    private Date birthday;

    private Double salary;
}
