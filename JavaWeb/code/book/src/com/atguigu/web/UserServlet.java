package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.code.kaptcha.Constants;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();


    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数 username
        String username = req.getParameter("username");
        // 调用userService.existsUsername();
        boolean existsUsername = userService.existsUsername(username);
        // 把返回的结果封装成为map对象
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername",existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);

        resp.getWriter().write(json);
    }

    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1、销毁 Session 中用户登录的信息（或者销毁 Session）
        req.getSession().invalidate();
//        2、重定向到首页（或登录页面）。
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * 处理登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 2、userService.login()登录处理业务
        User loginUser = userService.login(new User(null, username, password, null));
        // 3、根据login()方法返回结果判断登录是否成功
        if (loginUser == null) {
            // 把错误信息，和回显的表单项信息，保存到Request域中
            req.setAttribute("msg", "用户或密码错误");
            req.setAttribute("username", username);
            // 如果等于null，说明登陆失败！
            // 失败跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            // 登陆成功
            // 保存用户登录的信息到Session域中
            req.getSession().setAttribute("user", loginUser);

            // 成功跳到成功页面login_success.html
            System.out.println("登陆成功");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    /**
     * 处理注册
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        // 获取 Session 中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session 中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        // 2、检查验证码是否正确
        if (token != null && token.equalsIgnoreCase(code)) {
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
                userService.registUser(user);
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
