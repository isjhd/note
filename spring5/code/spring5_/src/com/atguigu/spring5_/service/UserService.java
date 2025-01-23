package com.atguigu.spring5_.service;

import com.atguigu.spring5_.dao.UserDao;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class UserService {
    // 创建 UserDao 类型属性，生成 set 方法
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add() {
        System.out.println("service add......");
        userDao.update();
    }
}
