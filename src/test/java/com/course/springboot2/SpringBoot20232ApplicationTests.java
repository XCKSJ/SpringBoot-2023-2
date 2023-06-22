package com.course.springboot2;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.course.springboot2.common.App;
import com.course.springboot2.common.App1;
import com.course.springboot2.common.App2;
import com.course.springboot2.domain.User;
import com.course.springboot2.mapper.UserMapper;
import com.course.springboot2.service.UserMyBatisService;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
@EnableAspectJAutoProxy
class SpringBoot20232ApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserMyBatisService userMyBatisService;

    @Autowired
    App app;
    @Autowired
    App1 app1;
    @Autowired
    App2 app2;

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

    // 查询 id <= 3 的用户信息，采用子查询
    @Test
    public void testInSqlWrapper(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id", "select id from t_user where id <= 3");

        List<User> l = userMapper.selectList(queryWrapper);

        l.forEach(x -> System.out.println(x));
    }

    // 修改（年龄大于20或邮箱为空）并且用户名中含有a的用户信息
    @Test
    public void testUpdateWrapper6(){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        // 1.wrapper 中带 set 方法，不需要定义实体对象
        updateWrapper.like("name", "a")
                .and(i -> i.gt("age", 20).or().isNull("email"));

        User u = new User();
        u.setName("王语嫣");

        int r = userMapper.update(u, updateWrapper);

        System.out.println("本次修改记录条数：" + r);
    }

    // 判断查询条件是否存在来加入条件
    @Test
    public void testCondQueryWrapper7(){
        String username = "a";
        String ageBegin = "10";
        String ageEnd = "30";

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        // StringUtils.isNotBlank() 判断字符串是否为空
        queryWrapper.like(StringUtils.isNotBlank(username), "name", username)
                .ge(StringUtils.isNotBlank(ageBegin), "age", ageBegin)
                .le(StringUtils.isNotBlank(ageEnd), "age", ageEnd);

        List<User> l = userMapper.selectList(queryWrapper);

        l.forEach(x -> System.out.println(x));
    }

    // 采用 Lambda 形式
    @Test
    public void testLambdaCondQueryWrapper7(){
        String username = "a";
        String ageBegin = "10";
        String ageEnd = "30";

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();

        // StringUtils.isNotBlank() 判断字符串是否为空
        queryWrapper.like(StringUtils.isNotBlank(username), User::getName, username)
                .ge(StringUtils.isNotBlank(ageBegin), User::getAge, ageBegin)
                .le(StringUtils.isNotBlank(ageEnd), User::getAge, ageEnd);

        List<User> l = userMapper.selectList(queryWrapper);

        l.forEach(x -> System.out.println(x));
    }

    // 使用 IService
    @Test
    public void testService1(){
        List<User> l = userMyBatisService.list();
        l.forEach(x -> System.out.println(x));
    }

    // 分页查询
    @Test
    public void testPageQuery9(){
        Page<User> page = userMyBatisService.page(new Page<>(1, 4));
        List<User> l = page.getRecords();
        l.forEach(x -> System.out.println(x));

        System.out.println("当前页：" + page.getCurrent());
        System.out.println("每页显示的条数：" + page.getSize());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("总页数：" + page.getPages());
        System.out.println("是否有上一页：" + page.hasPrevious());
        System.out.println("是否有下一页：" + page.hasNext());
    }
    // 测试AOP
    @Test
    public void testAop(){
        app.add();
    }
    @Test
    public void testAop1(){
        app1.add();
    }
    @Test
    public void testAop2(){
        app2.add();
    }

}
