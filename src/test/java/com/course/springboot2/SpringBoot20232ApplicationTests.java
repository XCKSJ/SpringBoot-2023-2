package com.course.springboot2;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    // 根据一组 id 查询
    @Test
    public void test1(){
        User u = userMapper.selectById(2);

        List<Long> idList = Arrays.asList(2L, 3L, 5L);
        List<User> l = userMapper.selectBatchIds(idList);
    }

    // 根据 id 逻辑删除
    @Test
    public void testLogicDel(){
        userMapper.deleteById(5);
    }

    // 根据条件查询数据
    @Test
    public void testQueryWrapper(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a")
                .between("age", 20, 30)
                .isNotNull("email");

        // 使用 mapper 中的方法，查询数据
        List<User> l = userMapper.selectList(queryWrapper);

        l.forEach(System.out::print);
    }

    // 根据条件进行排序
    @Test
    public void testQueryWrapper2(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("id");

        // 使用 mapper 中的方法，查询数据
        List<User> l = userMapper.selectList(queryWrapper);

        l.forEach(x -> System.out.println(x));
    }

    // 根据条件进行逻辑删除
    @Test
    public void testQueryWrapper3(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");

        // 使用 mapper 中的方法，删除数据
        userMapper.delete(queryWrapper);
    }

    // 根据条件修改数据
    @Test
    public void testQueryWrapper4(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a")
                .gt("age", 20)
                .or()
                .isNull("email");

        // 使用 mapper 中的方法，修改数据
        User u = new User();
        u.setAge(2);
        u.setEmail("aaa@163.com");
        userMapper.update(u, queryWrapper);
    }

    // 根据条件 修改数据
    @Test
    public void testQueryWrapper5(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a")
                .and(i -> i.gt("age", 20).or().isNull("email"));

        // 使用 mapper 中的方法，修改数据
        User user = new User();
        user.setAge(18);
        user.setEmail("user@atguigu.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("受影响的行数：" + result);
    }

}
