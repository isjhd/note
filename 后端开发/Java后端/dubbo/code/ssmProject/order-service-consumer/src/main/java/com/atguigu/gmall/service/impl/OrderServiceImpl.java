package com.atguigu.gmall.service.impl;

import com.atguigu.gmall.bean.UserAddress;
import com.atguigu.gmall.service.OrderService;
import com.atguigu.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserService userService;

    @Override
    public void initOrder(String userId) {
        System.out.println("用户id：" + userId);
        //1.查询用户的收货地址
        List<UserAddress> addressList = userService.getUserAddressList(userId);
        for(UserAddress userAddress : addressList) {
            System.out.println(userAddress.getUserAddress());
        }
    }
}
