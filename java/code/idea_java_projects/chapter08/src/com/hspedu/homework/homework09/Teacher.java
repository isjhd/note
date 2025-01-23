package com.hspedu.homework.homework09;

public class Teacher extends Person{
    private int work_age;

    public Teacher(String name, int age, char sex, int work_age) {
        super(name, age, sex);
        this.work_age = work_age;
    }

    public String teach() {
        return "我承认，我会认真教书。";
    }

    public String play() {
        return super.play() + "摇滚";
    }

    public String into() {
        return super.into() + "\n工龄：" + work_age + "\n" + teach() + "\n" + play();
    }

    public int getWork_age() {
        return work_age;
    }

    public void setWork_age(int work_age) {
        this.work_age = work_age;
    }
}
