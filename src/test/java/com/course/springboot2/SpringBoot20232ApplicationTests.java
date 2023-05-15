package com.course.springboot2;

import com.course.springboot2.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBoot20232ApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    public void getAllUsers(){
        userMapper.selectList(null).forEach(x -> System.out.println(x));
    }

}
