package com.hspedu.reflection.homework;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class Homework01 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
            IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {

        Class privateTest = Class.forName("com.hspedu.reflection.homework.PrivateTest");
        Object privateTest1 = privateTest.newInstance();
        Field name = privateTest.getDeclaredField("name");
        name.setAccessible(true);
        name.set(privateTest1, "my");

        Method getName = privateTest.getMethod("getName");
        System.out.println(getName.invoke(privateTest1));
    }
}
class PrivateTest{
    private String name = "hellokitty";

    public String getName() {
        return name;
    }
}