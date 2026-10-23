package cn.itcast.waterboss.dao;

import cn.itcast.waterboss.entity.Owners;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//业主信息数据访问类
public class OwnersDao {

    //增加业主
    public static void add(Owners owners) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = BaseDao.getConnection();
            stmt = conn.prepareStatement("insert into t_owners values(?,?,?,?,?,?,?)");
            stmt.setLong(1, owners.getId());//业主编号
            stmt.setString(2, owners.getName());//业主名称
            stmt.setLong(3, owners.getAddress());//地址编号
            stmt.setString(4, owners.getHouseNumber());//门牌号
            stmt.setString(5, owners.getWaterMeter());//水表编号
            stmt.setDate(6, new Date(owners.getAddDate().getTime()));//增加日期
            stmt.setLong(7, owners.getOwnerTypeId());//业主类型
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //关闭所有对象
            BaseDao.closeAll(null, stmt, conn);
        }

    }

    //修改业主
    public static void update(Owners owners) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = BaseDao.getConnection();
            stmt = conn.prepareStatement("update t_owners set name=?, addressid=?," +
                    "housenumber=?, watermeter=?, adddate=?, ownertypeid=? where id=?");
            stmt.setString(1, owners.getName());//业主名称
            stmt.setLong(2, owners.getAddress());//地址编号
            stmt.setString(3, owners.getHouseNumber());//门牌号
            stmt.setString(4, owners.getWaterMeter());//水表编号
            stmt.setDate(5, new Date(owners.getAddDate().getTime()));//增加日期
            stmt.setLong(6, owners.getOwnerTypeId());//业主类型
            stmt.setLong(7, owners.getId());//业主编号
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //关闭所有对象
            BaseDao.closeAll(null, stmt, conn);
        }

    }

    //删除业主
    public static void delete(Long id) {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {

            conn = BaseDao.getConnection();
            stmt = conn.prepareStatement("delete from t_owners where id=?");

            stmt.setLong(1, id);//业主编号
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //关闭所有对象
            BaseDao.closeAll(null, stmt, conn);
        }

    }

}
