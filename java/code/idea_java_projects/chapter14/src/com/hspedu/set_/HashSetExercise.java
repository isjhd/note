package com.hspedu.set_;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashSetExercise {
    public static void main(String[] args) {

        Set hashSet = new HashSet();
        hashSet.add(new Employee("唯", 18));
        hashSet.add(new Employee("唯", 18));
        hashSet.add(new Employee("唯", 18));
        System.out.println(hashSet);
    }
}
class Employee {
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age && name.equals(employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
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
}