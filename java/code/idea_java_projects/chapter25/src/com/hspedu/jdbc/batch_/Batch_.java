package com.hspedu.jdbc.batch_;

import com.hspedu.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/* @author  i-s-j-h-d
 * @version 1.0
 * 演示java的批处理
 */
public class Batch_ {

    @Test//传统方法，添加5000条数据到admin2
    public void noBatch() throws SQLException {

        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into admin2 values(null, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println("开始执行");
        long start = System.currentTimeMillis();//开始时间
        for (int i = 0; i < 5000; i++) {//执行5000次
            preparedStatement.setString(1, "jack" + i);
            preparedStatement.setString(2, "666");
            preparedStatement.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println("传统的方式 耗时=" + (end - start));
        //关闭连接
        JDBCUtils.close(null, preparedStatement, connection);
    }

    @Test//使用批量方式添加数据
    public void batch() throws SQLException {

        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into admin2 values(null, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println("开始执行");
        long start = System.currentTimeMillis();//开始时间
        for (int i = 0; i < 5000; i++) {//执行5000次
            preparedStatement.setString(1, "jack" + i);
            preparedStatement.setString(2, "666");
            //将sql 语句加入到批处理包中 -> 看源码
            preparedStatement.addBatch();
            //当有1000条记录时，在批量执行
            if((i + 1)% 1000 == 0) {//满1000条sql
                preparedStatement.executeBatch();
                //清空一把
                preparedStatement.clearBatch();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("批量的方式 耗时=" + (end - start));
        //关闭连接
        JDBCUtils.close(null, preparedStatement, connection);
    }
}
