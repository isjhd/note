package com.atguigu.service;

import com.atguigu.pojo.Cart;

/* @author  i-s-j-h-d
 * @version 1.0 */
public interface OrderService {
    public String createOrder(Cart cart, Integer userId);
}
