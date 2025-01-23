package com.hspedu.override_;

public class Person {

    private String name;
    private int age;

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person (String name, int age) {
        this.setName(name);
        this.setAge(age);
    }

    public String say () {
        return "姓名是：" + name + "，年龄是：" + age;
    }
}
