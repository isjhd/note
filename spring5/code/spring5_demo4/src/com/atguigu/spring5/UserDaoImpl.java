package com.atguigu.spring5;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class UserDaoImpl implements UserDao{

    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public String update(String id) {
        return id;
    }
}
