package com.atguigu.mv.controller;

import com.atguigu.mv.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Controller
public class ParamController {

    @RequestMapping("/testServletAPI")
    // 形参位置的request表示当前请求
    public String testServletAPI(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username:" + username + ", password:" + password);
        return "testRequestMapping";
    }

    @RequestMapping("/testParam")
    public String testParam(
            @CookieValue(value = "Idea-31038410") String cookie){
        System.out.println("cookie: " + cookie);
        return "testRequestMapping";
    }

    @RequestMapping("/testpojo")
    public String testPOJO(User user) {
        System.out.println(user);
        return "testRequestMapping";
    }


}
