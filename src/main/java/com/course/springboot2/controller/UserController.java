package com.course.springboot2.controller;

import com.course.springboot2.config.CommonConstant;
import com.course.springboot2.domain.Result;
import com.course.springboot2.domain.User;
import com.course.springboot2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.lang.model.element.VariableElement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/test1")
    public Result<User> searchUserInfoByName(@RequestParam("name") String name){
        return userService.getUserInfoByName(name);
    }

    // 查询 user 表的所有数据
    @RequestMapping("/getAll")
    public Result<List<User>> getAllUsers(){
        List<User> list = jdbcTemplate.query("select * from user", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String email = rs.getString("email");

                User u = new User();
                u.setId(id);
                u.setName(name);
                u.setAge(age);
                u.setGender(gender);
                u.setEmail(email);

                return u;
            }
        });
        return Result.ok(list);
    }

    // 添加 user
    @RequestMapping("/addUser")
    public Result<Integer> addUser(){
        int i = jdbcTemplate.update("insert into user(name,age,gender,email) values(?,?,?,?)","zhaoliu",21,"女","123456@gmail.com");
        return Result.ok(i);
    }
}
