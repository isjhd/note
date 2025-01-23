package com.isjhd.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.isjhd.mybatisplus.mapper.UserMapper;
import com.isjhd.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/* @author  i-s-j-h-d
 * @version 1.0 */
@SpringBootTest
public class MyBatisPlus {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01() {
        //查询用户名包含a, 年龄在20~30之间，邮箱信息不为null的用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","B")
                .between("age",10,30)
                .isNotNull("email");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test02() {
        //查询用户信息，按照年龄的降序排序，若年龄相同，则按照id升序排序
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("uid");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test03() {
        //删除邮箱地址为null的用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println("result: " + result);
    }

    @Test
    public void test04() {
        //将（年龄大于20并且用户名中包含有a）或邮箱为null的用户信息修改
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age",20)
                .like("name","B")
                .or()
                .isNull("email");
        User user = new User();
        user.setName("小明");
        user.setEmail("test@atguigu.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result: " + result);
    }

    @Test
    public void test05() {
        //将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
        //lambda 中的条件优先执行
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","a")
                .and(i->i.gt("age",20).or().isNull("email"));
        User user = new User();
        user.setName("小红");
        user.setEmail("test@atguigu.com");
        int result = userMapper.update(user, queryWrapper);
        System.out.println("result：" + result);
    }

    @Test
    public void test06() {
        //查询用户的用户名、年龄、邮箱信息
        //SELECT name,age,email FROM user WHERE is_deleted=0
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name","age","email");
        List<Map<String,Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test07() {
        //查询id小于等于100的用户信息
        //==> SELECT uid AS id,name,age,email,is_deleted FROM user WHERE is_deleted=0
        //      AND (uid IN (select uid from user where uid <= 100))
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("uid","select uid from user where uid <= 100");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test08() {
        //将用户名中包含a并且(年龄大于20或邮箱为null)的用户信息修改
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("name","a")
                .and(i->i.gt("age",20).or().isNull("email"));
        updateWrapper.set("name","小黑").set("email","abc@qq.com");
        int result = userMapper.update(null,updateWrapper);
        System.out.println(result);
    }

    @Test
    public void test09() {
        //定义查询条件，有可能为null（用户未输入或未选择）
        String username = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)) {
            //isNotBlank判断某个字符串是否不为空字符串，不为null，不为空白符
            queryWrapper.like("name",username);
        }
        if(ageBegin != null) {
            queryWrapper.ge("age",ageBegin);
        }
        if(ageEnd != null){
            queryWrapper.le("age",ageEnd);
        }
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test10(){
        //定义查询条件，有可能为null（用户未输入或未选择）
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username), "name",username)
                .ge(ageBegin != null, "age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test11() {
        //定义查询条件，有可能为null（用户未输入或未选择）
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //避免使用字符串表示字段，防止运行时错误
        queryWrapper.like(StringUtils.isNotBlank(username), User::getName, username)
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(ageEnd != null, User::getAge, ageEnd);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test12(){
        //将用户名中包含a并且(年龄大于20或邮箱为null)的用户信息修改
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(User::getName,"a")
                .and(i->i.gt(User::getAge,20).or().isNull(User::getEmail)); //lambda表达式内的逻辑优先运算
        updateWrapper.set(User::getName,"小黑").set(User::getEmail,"abc@qq.com");
        int result = userMapper.update(null,updateWrapper);
        System.out.println(result);
    }



}
