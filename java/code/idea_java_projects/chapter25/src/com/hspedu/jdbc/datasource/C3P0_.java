package com.hspedu.jdbc.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/* @author  i-s-j-h-d
 * @version 1.0
 * 演示c3p0的使用
 */
public class C3P0_ {

    //方式一：相关参数，在程序中指定user, url, password等
    @Test
    public void testC3P0_01() throws Exception {

        //1. 创建一个数据源对象
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        //2. 通过配置文件mysql.properties 获取相关连接的信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        //读取相关的属性值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        //给数据源 comboPooledDataSource 设置相关的参数
        //注意：连接管理是由 comboPooledDataSource 来管理
        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);

        //设置初始化连接数
        comboPooledDataSource.setInitialPoolSize(10);
        //最大连接数
        comboPooledDataSource.setMaxPoolSize(50);

        Connection connection = comboPooledDataSource.getConnection();//这个方法就是从 DataSource 接口实现的
        System.out.println("连接OK");
        connection.close();
    }


    //第二种方式 使用配置文件模板来完成
    //1. 将c3p0 提供的 c3p0.config.xml 拷贝到 src 目录下
    //2. 该文件指定了连接数据库和连接池的相关参数
    @Test
    public void testC3P0_02() throws Exception {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("isjhd");

        Connection connection = comboPooledDataSource.getConnection();
        System.out.println("连接OK");
        connection.close();
    }

}
