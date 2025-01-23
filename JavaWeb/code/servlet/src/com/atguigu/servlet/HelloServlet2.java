package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class HelloServlet2 extends HttpServlet {

    //  doGet() 在 get 请求的时候调用
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet2 的 doGet 方法");
    }

    //  doPost() 在 post 请求的时候调用
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet2 的 doPost 方法");
    }
}
