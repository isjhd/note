package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class RegistServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        // 2、检查验证码是否正确 == 写死，要求验证码为：abcde
        if ("abcde".equalsIgnoreCase(code)) {
            // 3.检查用户名是否可用
            if (userService.existsUsername(username)) {
                System.out.println("用户名【" + username + "】已存在");

                // 把回显信息，保存到Request域中
                req.setAttribute("msg", "验证码错误！！！");
                req.setAttribute("username", username);
                req.setAttribute("email", email);

                //  不可用    跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //  可用    调用Service保存到数据库，
                userService.registUser(new User(null, username, password, email));
                // 跳到注册成功本面regist_success.html
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);

                System.out.println("用户名【" + username + "】创建成功");
            }
        } else {
            // 把回显信息，保存到Request域中
            req.setAttribute("msg", "验证码错误！！！");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            System.out.println("验证码【" + code + "】错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }


    }
}
