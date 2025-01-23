package com.hspedu.homework.homework09;

public class Homework09 {
    public static void main(String[] args) {

        System.out.println("老师的信息：");
        Teacher t1 = new Teacher("撒瓦酱", 28, '女',5);
        Teacher t2 = new Teacher("五条悟", 24, '男',3);
        System.out.println(t1.into());
        System.out.println("-------------------------------------------");
        System.out.println("学生的信息：");
        Student s1 = new Student("平泽唯", 18, '女',"00023202");
        Student s2 = new Student("阿梓喵", 17, '女',"00023102");
        System.out.println(s1.into());
        System.out.println("---------------------------------------------");
        Person p [] = {t1, t2, s1, s2};
        new Person().sort(p);
        for (int i = 0; i < p.length; i++) {
            System.out.println("-------------------------------------------");
            System.out.println(p[i].into());
        }
        System.out.println("-----------------------------------------------");
        for (int i = 0; i < p.length; i++) {
            new Person().call(p[i]);
        }
    }
}
