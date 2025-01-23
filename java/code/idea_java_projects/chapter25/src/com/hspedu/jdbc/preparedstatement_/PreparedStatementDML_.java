package com.hspedu.jdbc.preparedstatement_;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class PreparedStatementDML_ {
    public static void main(String[] args) throws Exception{
        // 演示PreparedStatement使用

        //让用户输入管理员名和密码
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入需要添加的名字："); //next(): 当接收到空格或者 '就是表示结束
        String admin_name =scanner.nextLine(); //说明，如果希望看到SQL注入，这里需要用nextLine
        System.out.print("请输入需要添加的密码：");
        String admin_pwd = scanner.nextLine();

        //通过Properties对象获取配置文件的信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        //获取相关的值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        //1. 注册驱动
        Class.forName(driver); //建议写上

        //2. 得到连接
        Connection connection = DriverManager.getConnection(url, user, password);

        //3. 得到Statement
        // 3.1 组织SQL, SQL语句的？就相当于占位符
        // 添加记录
        String sql = "insert into admin values(?, ?)";
        // 3.2 preparedStatement 对象实现了 PreparedStatement 接口的实现类的对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 3.3 给 ？ 赋值
        preparedStatement.setString(1, admin_name);
        preparedStatement.setString(2, admin_pwd);

        //4. 执行 dml(update, insert, delete) 语句使用 executeUpdate()
        //   如果执行的是 select 语句使用 executeQuery
        int rows = preparedStatement.executeUpdate();
        System.out.println(rows > 0 ? "执行成功" : "执行失败");

        //5. 关闭连接
        preparedStatement.close();
        connection.close();
    }
}
