package com.hspedu.homework;

public class Homework03 {
    public static void main(String[] args) {

       Ordinary ordinary =  new Ordinary("平泽唯", 300, 30, 1.0);
        System.out.println(ordinary.print());//普通员工

        Manger manger = new Manger("撒瓦酱", 300, 30, 1.2, 1000);
        System.out.println(manger.print());//项目经理
    }
}
//普通员工
class Ordinary extends Employee1{

    public Ordinary(String name, double wages, int days, double grade) {
        super(name, wages, days, grade);
    }

    public String print() {
        return super.print() + "\t工资：" + (getWages()*getDays()*getGrade());
    }
}

//项目经理
class Manger extends Employee1{

    private int bonus;

    public Manger(String name, double wages, int days, double grade, int bonus) {
        super(name, wages, days, grade);
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public String print() {
        return super.print() + "\t工资：" + (bonus + getWages()*getDays()*getGrade());
    }
}

//父类：员工类。
class Employee1 {
    private String name;
    private double wages;//单日工资
    private int days;//工作天数
    private double grade;//等级

    public Employee1(String name, double wages, int days, double grade) {
        this.name = name;
        this.wages = wages;
        this.days = days;
        this.grade = grade;
    }

    public String print() {//打印基本工资
        return "姓名：" + name + "，单日工资：" + wages
                + "，工作天数：" + days + "，等级：" + grade + "。";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWages() {
        return wages;
    }

    public void setWages(double wages) {
        this.wages = wages;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}