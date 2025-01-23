package com.hspedu.outputstream;

/* @author  i-s-j-h-d
 * @version 1.0 */

import java.io.Serializable;

//如果需要序列化某个类的对象，实现Serializable
public class Dog implements Serializable {
    private String name;
    private int age;

    //serialVersionUID 序列化的版本号，可以提高兼容性
    private static final long serialVersionUID = 1L;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }
}
