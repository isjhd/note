package com.atguigu.servlet;
/* @author  i-s-j-h-d
 * @version 1.0 */

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ContextServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、获取web.xml中配置的上下文参数context-param
        ServletContext context = getServletConfig().getServletContext();
        System.out.println("context-param参数username的值是：" + context.getInitParameter("username"));
        System.out.println("context-param参数password的值是：" + context.getInitParameter("password"));

        // 2、获取当前的工程路径，格式: /工程路径
        System.out.println("当前工程路径：" + context.getContextPath());

        // 3、获取工程部署后在服务器硬盘上的绝对路径
        //      / 斜杆被服务器解析地址为：http://ip:port/工程名/
        System.out.println("工程部署的路径是：" + context.getRealPath("/"));
        System.out.println("工程下css目录的绝对路径是：" + context.getRealPath("/css"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
