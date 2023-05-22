package com.course.springboot2;

import com.course.springboot2.domain.User;
import com.course.springboot2.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class SpringBoot20232ApplicationTests {
    @Autowired
    UserMapper userMapper;

    // 查询所有用户
    @Test
    public void getAllUsers(){
        userMapper.selectList(null).forEach(x -> System.out.println(x));
    }

    // 添加用户
    @Test
    public void testInsertUser(){
        User u = new User();
        u.setName("老杨");
        u.setAge(100);
        u.setGender("男");
        u.setEmail("12346@aliyun.com");
        userMapper.insert(u);
    }

    // 根据 id 删除用户
    @Test
    public void delUserById(){
        userMapper.deleteById(5);
    }

    // 根据 id 删除多个用户
    @Test
    public void delUserByIds(){
        List<Long> l = Arrays.asList(3L, 8L, 200L);
        userMapper.deleteBatchIds(l);
    }

    // 根据 name 和 age 删除用户
    @Test
    public void delUserByMap(){
        HashMap<String, Object> m = new HashMap<>();
        m.put("name", "欧阳锋");
        m.put("age", 100);
        userMapper.deleteByMap(m);
    }

    // 根据 id 修改用户
    @Test
    public void updateUserById(){
        User u = new User();
        u.setId(5);
        u.setName("aaaa");
        u.setAge(100);
        userMapper.updateById(u);
    }

    @Test
    public void test1(){
        User u = userMapper.selectById(2);

        List<Long> idList = Arrays.asList(2L, 3L, 5L);
        List<User> l = userMapper.selectBatchIds(idList);
    }

    @Test
    public void testLogicDel(){
        userMapper.deleteById(5);
    }

}
