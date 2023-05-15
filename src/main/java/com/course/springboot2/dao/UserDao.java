package com.course.springboot2.dao;

import com.course.springboot2.domain.User;
import org.springframework.stereotype.Repository;


@Repository
public class UserDao {
    public User findUserInfoByName(String name){
        if("Wang".equals(name)){
            return null;
        }else{
            User u = new User();
            u.setName("Yang");
            u.setAge(20);

            return u;
        }
    }
}
