package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/* @author  i-s-j-h-d
 * @version 1.0 */public class UserServiceTest {

     UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null, "轻音少女", "123456", "kon@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "轻音少女", "123456", null)));
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("轻音少女")) {
            System.out.println("用户名已存在！");
        } else {
            System.out.println("用户名可用！");
        }
    }
}