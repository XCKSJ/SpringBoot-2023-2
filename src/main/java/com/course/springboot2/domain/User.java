package com.course.springboot2.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
//@TableName("t_user")
public class User {
    //@TableId("id1")
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    //@TableField("email1")
    private String email;
    private String managerId;
    private Date createTime;
    @TableLogic(value = "1", delval = "0")
    private String isDeleted;

    public User(){}
}
