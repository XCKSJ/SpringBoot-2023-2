package com.course.springboot2.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
//@TableName("t_user")
public class User {
    //@TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    //@TableField("email1")
    private String email;
    private String managerId;
    private Date createTime;
    //@TableLogic(value = "1", delval = "0")
    //private String isDeleted;

    public User(){}
}
