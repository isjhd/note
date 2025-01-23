package com.atguigu.servlet;

import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class Download extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1、获取要下载的文件名
                String downloadFileName = "2.png";
//        2、读取要下载的文件内容(通过ServletContext对象可以读取)
                ServletContext servletContext = getServletContext();
                InputStream resourceAsStream = servletContext.getResourceAsStream("/file/" + downloadFileName);
//        4、在回传前，通过响应头告诉客户端返回的数据类型
                // 获取要下载的文件类型
                String mimeType = servletContext.getMimeType("/file/" + downloadFileName);
                resp.setContentType(mimeType);
//        5、还要告诉客户端收到的数据是用于下载使用（还是使用响应头）
                // Content-Disposition响应头 表示收到的数据怎么处理
                // attachment 表示附件，表示下载使用
                // filename= 表示指定下载的文件名
                // url编码是把文字转换成为%xx%xx的格式
                if (req.getHeader("User-Agent").contains("Firefox")) {
                    resp.setHeader("Content-Disposition",
                            "attachment; fileName=" + "=?utf-8?B?"+ new BASE64Encoder().encode("中文.jpg".getBytes("utf-8")) + "?=");
                } else {
                    resp.setHeader("Content-Disposition",
                            "attachment; filename=" + URLEncoder.encode("中文.png", "UTF-8"));
                }
//        3、把下载的文件内容回传给客户端
                // 获取响应的输出流
                OutputStream outputStream = resp.getOutputStream();
                // 读取输入流中全部的数据，复制给输出流，输出给客户端
                IOUtils.copy(resourceAsStream,outputStream);

    }
}
