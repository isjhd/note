package com.atguigu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Controller
public class UserController {

    /**
     *  使用 RESTFul 模拟用户资源的增删改查
     *  /user    GET    查询所有用户信息
     *  /user/1  GET    根据用户id查询用户信息
     *  /user    POST   添加用户信息
     *  /user    DELETE 删除用户信息
     *  /user    PUT    修改用户信息
     */

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getAllUser(){
        System.out.println("查询所有用户信息");
        return "success";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUserById(){
        System.out.println("根据id查询用户信息");
        return "success";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String insertUser(String username, String password){
        System.out.println("添加用户信息：" + username + ", " + password);
        return "success";
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String updateUser(String username, String password){
        System.out.println("修改用户信息：" + username + ", " + password);
        return "success";
    }

    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    public String deleteUser(String username, String password){
        System.out.println("删除用户信息：" + username + ", " + password);
        return "success";
    }

}
