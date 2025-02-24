package com.hspedu.jdbc.transaction_;

import com.hspedu.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/* @author  i-s-j-h-d
 * @version 1.0
 * 演示jdbc 中如何使用事务
 */
public class Transaction_ {

    @Test //没有使用事务
    public void noTransaction() {
        //操做转账的业务

        //1. 得到连接
        Connection connection = null;

        //2. 组织一个sql
        String sql1 = "update account set balance = balance - 100 where id = 1";
        String sql2 = "update account set balance = balance + 100 where id = 2";
        PreparedStatement preparedStatement = null;
        //3. 创建PreparedStatement 对象
        try {
            connection = JDBCUtils.getConnection(); //在默认情况下，connection是默认自动提交
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.executeUpdate();// 执行第一条sql语句

            int i = 1 / 0; //抛出异常
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.executeUpdate();// 执行第二条sql语句

            // 这里提交事务
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.close(null, preparedStatement, connection);
        }
    }

    @Test //事务来解决
    public void useTransaction() {
        //操做转账的业务

        //1. 得到连接
        Connection connection = null;

        //2. 组织一个sql
        String sql1 = "update account set balance = balance - 100 where id = 1";
        String sql2 = "update account set balance = balance + 100 where id = 2";
        PreparedStatement preparedStatement = null;
        //3. 创建PreparedStatement 对象
        try {
            connection = JDBCUtils.getConnection(); //在默认情况下，connection是默认自动提交
            //将 connection 设置为不自动提交
            connection.setAutoCommit(false); // 开启了事务
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.executeUpdate();// 执行第一条sql语句

            int i = 1/0; //抛出异常
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.executeUpdate();// 执行第二条sql语句

        } catch (SQLException e) {
            // 这里我们可以进行回滚，即撤销执行的SQL
            // 默认回滚到事务开始的状态
            System.out.println("执行发生了的异常，撤销执行的sql");
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.close(null, preparedStatement, connection);
        }
    }

}
