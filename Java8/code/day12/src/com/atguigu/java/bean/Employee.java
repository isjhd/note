package com.atguigu.java.bean;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class Employee {

    private String id;
    private String name;
    private int age;
    private double salary;

    public Employee(String id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Employee(String id) {
        this.id = id;
        System.out.println("Employee(String id)......");
    }

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("Employee(String id, String name)...");
    }

    public Employee() {
        System.out.println("Employee().....");
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
