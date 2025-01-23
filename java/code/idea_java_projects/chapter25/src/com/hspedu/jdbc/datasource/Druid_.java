package com.hspedu.jdbc.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/* @author  i-s-j-h-d
 * @version 1.0
 * 测试druid的使用
 */
public class Druid_ {

    @Test
    public void testDruid() throws Exception{
        //1. 加入 Druid jar包
        //2. 加入 配置文件 druid.properties, 将该文件拷贝到项目的src目录
        //3. 创建Properties对象，读取配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\druid.properties"));

        //4. 创建一个指定参数的数据库连接池，Druid连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        Connection connection = dataSource.getConnection();
        System.out.println("连接成功");
        connection.close();
    }
}
