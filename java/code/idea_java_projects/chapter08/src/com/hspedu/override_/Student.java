package com.hspedu.override_;

public class Student extends Person{

    private int id;
    private double score;

    public Student (String name, int age, int id, double score) {
        super(name, age);
        this.setId(id);
        this.setScore(score);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String say () {
        return super.say() + "，id属性：" + id + "，分数是：" + score;
    }
}
