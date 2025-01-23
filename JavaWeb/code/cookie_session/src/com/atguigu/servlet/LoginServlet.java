package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class LoginServlet extends BaseServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if ("wzg168".equals(username) && "123456".equals(password)) {
            // 登陆成功
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(60*2);// 当前Cookie 2小时内有效
            resp.addCookie(cookie);
            System.out.println("登陆成功");
        } else {
            // 登陆失败
            System.out.println("登陆失败");
        }
    }
}
