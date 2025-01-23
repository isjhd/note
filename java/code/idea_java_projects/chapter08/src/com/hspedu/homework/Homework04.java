package com.hspedu.homework;

public class Homework04 {
    public static void main(String[] args) {

        Worker worker = new Worker("建筑工人", 10000);
        worker.setSalMonth(15);//一年发多少个月的工资
        System.out.println(worker.baseSalary());//工人类

        Scientist scientist = new Scientist("爱因斯坦", 25000);
        scientist.setSalMonth(15);//一年发多少个月的工资
        scientist.setBonus(10000);
        System.out.println(scientist.baseSalary());//科学家

    }
}
//工人类
class Worker extends Employee2{
    public Worker(String name, double salary) {
        super(name, salary);
    }

    public String baseSalary() {
        return super.baseSalary();
    }
}

//科学家类
class Scientist extends Employee2{
    private double bonus;//年终奖

    public Scientist(String name, double salary) {
        super(name, salary);
    }

    public String baseSalary() {
        return "姓名：" + getName() + "，全年工资：" + (getSalary()*getSalMonth() + bonus);
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}

//父类：员工类。
class Employee2 {
    private String name;
    private double salary;//月工资
    private int salMonth = 12;//一年12个月。

    public Employee2(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String baseSalary() {//输出基本工资
        return "姓名：" + name + "，全年工资：" + salary*salMonth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getSalMonth() {
        return salMonth;
    }

    public void setSalMonth(int salMonth) {
        this.salMonth = salMonth;
    }
}