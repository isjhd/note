package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
/* @author i-s-j-h-d
 * @version 1.0
 * 基于druid数据库连接池的工具类
 */
public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();

    static {
        try {
            Properties properties = new Properties();
            // 读取 jdbc.properties 属性配置文件
            InputStream inputStream =
                    JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 从流中加载数据
            properties.load(inputStream);
            // 创建 数据库连接 池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取数据库连接池中的连接
     * @return 如果返回 null,说明获取连接失败<br/>有值就是获取连接成功
     */
    public static Connection getConnection(){
        Connection conn = conns.get();

        if (conn == null) {
            try {
                conn = dataSource.getConnection();
                conns.set(conn); //保存到 ThreadLocal 对象中，供后面的jdbc操作使用
                conn.setAutoCommit(false); // 设置手动管理事务
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return conn;
    }

    /**
     * 提交事务，并关闭释放连接
     */
    public static void commitAndClose() {
        Connection connection = conns.get();
        if (connection != null) { // 如果不等于null，说明之前使用过连接，操作过数据库
            try {
                connection.commit(); // 提交事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close(); // 关闭连接，释放资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // 一定要执remove操作，否则就会出错。(因为Tomcat服务器底层使用了线程池技术)
        conns.remove();
    }

    /**
     * 回滚事务，并关闭释放连接
     */
    public static void rollbackAndClose() {
        Connection connection = conns.get();
        if (connection != null) { // 如果不等于null，说明之前使用过连接，操作过数据库
            try {
                connection.rollback(); // 回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close(); // 关闭连接，释放资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // 一定要执remove操作，否则就会出错。(因为Tomcat服务器底层使用了线程池技术)
        conns.remove();
    }


    /**
     * 关闭连接，放回数据库连接池
     * @param conn
     */
//    public static void close(Connection conn){
//        if (conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
