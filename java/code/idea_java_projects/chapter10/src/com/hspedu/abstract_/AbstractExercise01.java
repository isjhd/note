package com.hspedu.abstract_;

public class AbstractExercise01 {
    public static void main(String[] args) {

    }
}
abstract class Employee {
    private String name;
    private String ID;
    private double salary;

    public Employee(String name, String ID, double salary) {
        this.name = name;
        this.ID = ID;
        this.salary = salary;
    }
    public abstract void work();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}

class Manager extends Employee{
    private double bonus = 1000;

    public Manager(String name, String ID, double salary, double bonus) {
        super(name, ID, salary);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public void work() {
        System.out.println("普通员工" + getName() + "，的奖金是：" + (getSalary() + bonus));
    }

}