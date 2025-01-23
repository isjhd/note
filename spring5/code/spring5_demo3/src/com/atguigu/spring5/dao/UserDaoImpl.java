package com.atguigu.spring5.dao;

import org.springframework.stereotype.Repository;

/* @author  i-s-j-h-d
 * @version 1.0 */
@Repository(value="userDaoImpl1")
public class UserDaoImpl implements UserDao{

    @Override
    public void add() {
        System.out.println("dao add......");
    }
}
