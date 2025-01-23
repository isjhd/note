package com.hspedu.reflection.homework;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class Homework02 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException, NoSuchMethodException, InvocationTargetException {

        Class files = Class.forName("java.io.File");
        Constructor [] file = files.getDeclaredConstructors();
        for (Constructor constructor : file) {
            System.out.println(constructor.toString());
        }

        Constructor declaredConstructor = files.getDeclaredConstructor(String.class);
        Object file1 = declaredConstructor.newInstance("D:\\ab.txt");
        Method createNewFile = files.getDeclaredMethod("createNewFile");
        createNewFile.invoke(file1);
    }
}