package com.atguigu.spring5.service;

import com.atguigu.spring5.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Service
//@Transactional(readOnly = false)
public class UserService {

    // 注入dao
    @Autowired
    private UserDao userDao;

    // 转账的方法
    @Transactional
    public void accountMoney() {
        // lucy少100
        userDao.reduceMoney();

//        int i = 10/0;

        // mary多100
        userDao.addMoney();
    }

}
