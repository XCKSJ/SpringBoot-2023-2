package com.course.springboot2.service;

import com.course.springboot2.config.CommonConstant;
import com.course.springboot2.dao.UserDao;
import com.course.springboot2.domain.Result;
import com.course.springboot2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public Result<User> getUserInfoByName(String name){
        User u = userDao.findUserInfoByName(name);
        if(u == null){
            return Result.ok(CommonConstant.NO_RECORD, CommonConstant.NO_RECORD_MSG, null);
        }
        return Result.ok(CommonConstant.SUCCESS, CommonConstant.SUCCESS_MSG, u);
    }
}
