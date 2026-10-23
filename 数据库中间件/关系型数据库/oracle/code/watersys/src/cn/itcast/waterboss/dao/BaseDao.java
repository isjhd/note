package cn.itcast.waterboss.dao;


import java.sql.*;

//基本数据访问类(获取数据库连接 关闭资源)
public class BaseDao {

    static {
        //加载驱动
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //获取数据库连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@192.168.80.10:1521:orcl", "wateruser", "isjhd");
    }

    //关闭资源
    public static void closeAll(ResultSet rs, Statement stmt, Connection conn) {

        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
