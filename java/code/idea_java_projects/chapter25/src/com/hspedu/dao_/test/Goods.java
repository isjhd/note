package com.hspedu.dao_.test;

import com.hspedu.dao_.dao.ActorDAO;
import com.hspedu.dao_.dao.GoodsDao;
import com.hspedu.dao_.domain.Actor;
import org.junit.Test;

import java.util.List;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class Goods {

    //测试ActorDAO 对actor表crud操作
    @Test
    public void testGoodsDao() {

        GoodsDao goodsDao = new GoodsDao();
        //1. 查询
        List<Actor> actors = goodsDao.queryMulti("select * from actor where id >= ?", Actor.class, 1);
        System.out.println("===查询结果===");
        for (Actor actor : actors) {
            System.out.println(actor);
        }

        //2. 查询单行记录
        Actor actor = goodsDao.querySingle("select * from actor where id = ?", Actor.class, 1);
        System.out.println("====查询单行结果===");
        System.out.println(actor);

        //3. 查询单行单列
        Object o = goodsDao.queryScalar("select name from actor where id = ?", 1);
        System.out.println("===查询单行单列值===");
        System.out.println(o);

        //4. dml操作 insert, update, delete
        int update = goodsDao.update("insert into actor values(null, ?, ?, ?, ?)",
                "周星驰", "男", "2000-11-11", "999");
        System.out.println(update > 0 ? "执行成功" : "执行没有影响表");
    }
}
