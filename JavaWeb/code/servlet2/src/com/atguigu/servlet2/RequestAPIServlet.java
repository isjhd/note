package com.atguigu.servlet2;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class RequestAPIServlet extends HttpServlet {

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException, IOException {

//        i. getRequestURI() 获取请求的资源路径
        System.out.println("URI =>" + req.getRequestURI());
//        ii. getRequestURL() 获取请求的统一资源定位符（绝对路径）
        System.out.println("URL =>" + req.getRequestURL());
//        iii. getRemoteHost() 获取客户端的 ip 地址
            /*
                在IDEA中，使用localhost访问时，得到的客户端ip地址是 ===>>> 127.0.0.1
                在IDEA中，使用127.0.0.1访问时，得到的客户端ip地址是 ===>>> 127.0.0.1
                在IDEA中，使用 真实ip 访问时，得到的客户端ip地址是 ==>>> 真实的客户端ip地址
            */
        System.out.println("客户端 ip地址 =>" + req.getRemoteHost());
//        iv. getHeader() 获取请求头
        System.out.println("请求头User-Agent =>" + req.getHeader("User-Agent"));
//        vii. getMethod() 获取请求的方式 GET 或 POST
        System.out.println("请求的方式 =>" + req.getMethod());
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
