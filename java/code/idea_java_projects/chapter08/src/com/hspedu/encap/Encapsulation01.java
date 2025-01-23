package com.hspedu.encap;

import java.sql.SQLOutput;

public class Encapsulation01 {

    public static void main(String[] args) {

        Person person = new Person();
        person.setName("coke");
        person.setAge(18);
        person.setSalary(10000.0);
        System.out.println(person.info());


    }
}
class Person {

    public String name;
    private int age;
    private double salary;

    public void setName (String name) {
        if (name.length() >= 2 && name.length() <= 6) {
            this.name = name;
        } else {
            System.out.println("name的长度需要在2~6个字符之间");
            this.name = "jack";
        }
    }

    public String getName () {
        return name;
    }

    public void setAge (int age) {
        if (age >= 0 && age <= 120) {
            this.age = age;
        } else {
            System.out.println("年龄需要在0~120岁");
            this.age = 18;
        }
    }

    public int getAge () {
            return age;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public String info () {

        return "信息如下：name=" + name + ", age=" + age + ", salary=" + salary;
    }

}

