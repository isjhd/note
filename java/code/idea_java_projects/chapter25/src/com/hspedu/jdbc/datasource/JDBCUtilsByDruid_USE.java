package com.hspedu.jdbc.datasource;

import com.hspedu.dao_.utils.JDBCUtilsByDruid;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class JDBCUtilsByDruid_USE {

    @Test//测试类
    public void testSelect() {// 查询

        System.out.println("使用 druid 方式完成");

        //1. 得到连接
        Connection connection = null;

        //2. 组织一个sql
        String sql = "select * from actor";
        PreparedStatement preparedStatement = null;
        ResultSet set = null;

        //3. 创建PreparedStatement 对象
        try {
            connection = JDBCUtilsByDruid.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            //执行，得到结果集
            set = preparedStatement.executeQuery();
            // 遍历该结果集
            while (set.next()) {
                int id = set.getInt("id");
                String name = set.getString("name");
                String sex = set.getString("sex");
                Date borndate = set.getDate("borndate");
                String phone = set.getString("phone");
                System.out.println(id + "\t" + name + "\t" +
                        sex + "\t" + borndate + "\t" + phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtilsByDruid.close(set, preparedStatement, connection);
        }
    }



    //使用土方法来解决ResultSer ==(封装)==> ArrayList
    @Test
    public ArrayList<Actor> testSelecToArrayList() {// 查询

        System.out.println("使用 druid 方式完成");

        //1. 得到连接
        Connection connection = null;

        //2. 组织一个sql
        String sql = "select * from actor";
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        ArrayList<Actor> list = new ArrayList<>();//创建ArrayList对象，存放actor对象
        //3. 创建PreparedStatement 对象
        try {
            connection = JDBCUtilsByDruid.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            //执行，得到结果集
            set = preparedStatement.executeQuery();
            // 遍历该结果集
            while (set.next()) {
                int id = set.getInt("id");
                String name = set.getString("name");
                String sex = set.getString("sex");
                Date borndate = set.getDate("borndate");
                String phone = set.getString("phone");
                //把得到的resultset的记录，封装到Actor对象，放入到list集合
                list.add(new Actor(id, name, sex, borndate, phone));
            }

            System.out.println("list集合数据=" + list);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtilsByDruid.close(set, preparedStatement, connection);
        }
        //因为ArrayList 和 connection 没有任何关联，所以该集合可以复用
        return list;
    }

}
