package com.atguigu.test;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class OrderDaoTest {

    @Test
    public void saveOrder() {

        OrderDao orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("1234567890", new Date(), new BigDecimal(100), 0, 1));

    }
}