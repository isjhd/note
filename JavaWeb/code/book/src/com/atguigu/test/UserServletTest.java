package com.atguigu.test;

import java.lang.reflect.Method;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class UserServletTest {

    public void login() {
        System.out.println("login()方法调用");
    }

    public void regist() {
        System.out.println("regist()方法调用");
    }

    public void updateUser() {
        System.out.println("updateUser()方法调用");
    }

    public void updateUserPassword() {
        System.out.println("updateUserPassword()方法调用");
    }

    public static void main(String[] args) {
        String action = "regist";

        try {
            // 获取action业务鉴别字符串，获取相应的业务 方法反射对象
            Method method = UserServletTest.class.getDeclaredMethod(action);

            // 调用目标业务 方法
            method.invoke(new UserServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
