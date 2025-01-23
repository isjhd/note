package com.hspedu.homework.homework09;

public class Person {
    private String name;
    private int age;
    private char sex;
    private String play;
    Person temp [] = null;
    public Person() {

    }

    public Person(String name, int age, char sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public void call(Person p) {
        if (p instanceof Student) {
            System.out.println(((Student)p).study());
        } else if (p instanceof Teacher) {
            System.out.println(((Teacher)p).teach());
        }
    }

    public void sort(Person p []) {
        temp = new Person[p.length];//临时变量
        for (int i = 1; i < p.length; i++) {
            for (int j = 0; j < p.length - i; j++) {
                if (p[j].age > p[j+1].age) {
                    temp[j] = p[j+1];
                    p[j+1] = p[j];
                    p[j]= temp[j];
                }
            }
        }
    }
    public String into() {
        return "姓名：" + name + "\n年龄：" + age + "\n性别：" + sex;
    }

    public String play() {
        return name + "爱玩";
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

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
}
