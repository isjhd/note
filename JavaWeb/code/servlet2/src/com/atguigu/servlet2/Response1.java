package com.atguigu.servlet2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class Response1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("曾到此一游 Response1");

        // 请求重定向的第一种方案：
            // 设置响应状态码302，表示重定向，(已搬迁)
            // resp.setStatus(302);
            // 设置响应头，说明 新的地址在哪里
            // resp.setHeader("Location", "http://localhost:8080/servlet2/response2");

        // 请求重定向的第二种方案（推荐使用）：
            resp.sendRedirect("http://localhost:8080/servlet2/response2");
    }
}
