package com.course.springboot2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.course.springboot2.domain.User;
import com.course.springboot2.mapper.UserMapper;
import com.course.springboot2.service.UserMyBatisService;
import org.springframework.stereotype.Service;

@Service
public class UserMyBatisServiceImpl extends ServiceImpl<UserMapper, User> implements UserMyBatisService {
}
