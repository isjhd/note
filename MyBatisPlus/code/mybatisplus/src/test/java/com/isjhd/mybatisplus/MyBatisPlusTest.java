package com.isjhd.mybatisplus;

import com.isjhd.mybatisplus.mapper.UserMapper;
import com.isjhd.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* @author  i-s-j-h-d
 * @version 1.0 */
@SpringBootTest
public class MyBatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectLis() {
        //通过条件构造器查询一个list集合，若没有条件，则可以设置null为参数
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void testInsert() {
        //实现新增用户信息
        //INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        user.setEmail("zhangSan@atguigu.com");
        int result = userMapper.insert(user);
        System.out.println("result: " + result);
    }

    @Test
    public void testDelete() {
        //通过id删除用户信息
        //DELETE FROM user WHERE id=?
//        int result = userMapper.deleteById(1775452186157543425L);
//        System.out.println("result: " + result);

//        Map<String,Object> map = new HashMap<>();
//        map.put("name","张三");
//        map.put("age", 23);
//        int result = userMapper.deleteByMap(map);
//        System.out.println("result: " + result);

        //通过多个id批量删除
        //DELETE FROM user WHERE id IN ( ? , ? , ? )
        List<Long> list = Arrays.asList(1L,2L,3L);
        int result = userMapper.deleteBatchIds(list);
        System.out.println("result: " + result);

    }

    @Test
    public void testUpdate() {
        //UPDATE user SET name=?, age=? WHERE id=?
        User user = new User();
        user.setId(4L);
        user.setName("李四");
        user.setEmail("lisi@qq.com");
        int result = userMapper.updateById(user);
    }

    @Test
    public void testSelect() {
//        User user = userMapper.selectById(1L);
//        System.out.println(user);

        //根据多个id查询多个用户信息
        //SELECT id,name,age,email FROM user WHERE id IN ( ? , ? )
//        List<Long> idList = Arrays.asList(4L, 5L);
//        List<User> list = userMapper.selectBatchIds(idList);
//        list.forEach(System.out::println);

        //查询所有用户信息
        //SELECT id,name,age,email FROM user
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);

//        Map<String,Object> map = userMapper.selectMapById(1L);
//        System.out.println(map);
    }

}
