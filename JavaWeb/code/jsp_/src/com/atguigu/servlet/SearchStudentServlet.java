package com.atguigu.servlet;

import com.atguigu.pojo.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class SearchStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求的参数
        // 发sql语句查询学生的信息
        // 使用for循环生成查询到的数据做模拟
        List<Student> studentList = new ArrayList<Student>();
        for (int i = 1; i < 10; i++) {
            studentList.add(new Student(i, "name"+i, 18+i, "phone"+i));
        }
        // 保存查询到的结果（学生信息）到request域中
        req.setAttribute("stuList", studentList);
        // 请求转发到showStudent.jsp页面
        req.getRequestDispatcher("/test/test2.jsp").forward(req, resp);
    }
}
