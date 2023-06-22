package com.course.springboot2.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.springboot2.domain.Result;
import com.course.springboot2.domain.User;
import com.course.springboot2.service.UserMyBatisService;
import com.course.springboot2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.ElementType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


@RestController
//@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserMyBatisService userMyBatisService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/test1")
    public Result<User> searchUserInfoByName(@RequestParam("name") String name){
        return userService.getUserInfoByName(name);
    }

    // 查询 user 表的所有数据
    @RequestMapping("/getAll")
    public Result<List<User>> getAllUsers(){
        List<User> list = jdbcTemplate.query("select * from t_user", new RowMapper<User>() {
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
        int i = jdbcTemplate.update("insert into t_user(name,age,gender,email) values(?,?,?,?)","zhaoliu",21,"女","123456@gmail.com");
        return Result.ok(i);
    }

    // 根据 id 删除
    @RequestMapping("/delete")
    public Result<Integer> delUserById(String id){
        int i = jdbcTemplate.update("delete from t_user where id = ?", id);
        return Result.ok(i);
    }

    // 根据 id 修改 name
    @RequestMapping("/updateUserById")
    public Result<Integer> updateUserById(String id, @RequestParam("name") String newName){
        int i = jdbcTemplate.update("update t_user set name = ? where id = ?", newName, id);
        return Result.ok(i);
    }

    // 根据 id 查询
    @RequestMapping("/getById")
    public Result<List<Map<String, Object>>> getById(String id){
        List<Map<String, Object>> l = jdbcTemplate.queryForList("select * from user where id > ?", id);
        return Result.ok(l);
    }

    // 统计男女各有多少人
    @RequestMapping("/getByGender")
    public Result<List<Map<String, Object>>> getByGender(){
        List<Map<String, Object>> l = jdbcTemplate.queryForList("select gender as '性别',count(gender) as '人数' from user group by gender");
        return Result.ok(l);
    }

    // 分页查询 user 信息
    @RequestMapping("/userPage")
    public Result<Page<User>> userPageBySearch(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage,
                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                   @RequestParam(value = "search", defaultValue = "") String search){
        Page<User> page;
        if (search.replaceAll("", "").equals("")){
            page = userMyBatisService.page(new Page<>(currentPage, pageSize));
        } else {
            page = userMyBatisService.page(new Page<>(currentPage, pageSize), Wrappers.<User>lambdaQuery().like(User::getName, search));
        }

        return Result.ok(page);
    }

    // 保存 user 信息
    @PostMapping("/save")
    public Result<Boolean> save(User u){
        boolean t = userMyBatisService.saveOrUpdate(u);
        return Result.ok(t);
    }

    // 根据 id 删除 user 信息
    @PostMapping("/deleteById")
    public Result<Boolean> deleteById(User u){
        boolean t = userMyBatisService.removeById(u.getId());
        return Result.ok(t);
    }
}
