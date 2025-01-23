package com.atguigu.spring5.service;

import com.atguigu.spring5.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Service
public class UserService {

    //定义 dao 类型属性
    //不需要添加 set 方法
    //添加注入属性注解
    @Autowired // 根据类型进行注入
    @Qualifier(value = "userDaoImpl1") // 根据名称进行注入
    private UserDao userDao;

    public void add() {
        System.out.println("service add.....");
        userDao.add();
    }
}
