package com.course.springboot2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.course.springboot2.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
