package com.atguigu.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class HelloServlet implements Servlet{

    public HelloServlet() {
        System.out.println("第一步：执行Servlet构造器方法");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("第二步：执行init初始化方法");

        //1、可以获取 Servlet 程序的别名 servlet-name 的值
        System.out.println("HelloServlet 程序的别名是:" + servletConfig.getServletName());
        // 2、获取初始化参数 init-param
        System.out.println("初始化参数 username 的值是;" + servletConfig.getInitParameter("username"));
        System.out.println("初始化参数 url 的值是;" + servletConfig.getInitParameter("url"));
        // 3、获取 ServletContext 对象
        System.out.println(servletConfig.getServletContext());
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /*
        service方法是专门用来处理请求和响应的
    */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("第三步：执行service方法");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        // 获取请求的方式
        String method = httpServletRequest.getMethod();

        if("GET".equals(method)) {
            doGet();
        } else if ("POST".equals(method)){
            doPost();
        }
    }

    // 做get请求的操作
    public void doGet() {
        System.out.println("get请求");
    }

    // 做post请求的操作
    public void doPost() {
        System.out.println("post请求");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("第四步：执行destroy销毁方法");
    }
}
