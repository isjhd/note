package com.hspedu.homework.homework09;

public class Student extends Person{
    private String stu_id;

    public Student(String name, int age, char sex, String stu_id) {
        super(name, age, sex);
        this.stu_id = stu_id;
    }

    public String study() {
        return "我承认，我会好好学习。";
    }

    public String play() {
        return super.play() + "吉他";
    }

    public String into() {
        return super.into() + "\n学号：" + stu_id + "\n" + study() + "\n" + play();
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

}
