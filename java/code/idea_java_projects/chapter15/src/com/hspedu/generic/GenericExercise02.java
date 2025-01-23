package com.hspedu.generic;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

import java.util.*;

public class GenericExercise02 {
    public static void main(String[] args) {

        Employee employee1 = new Employee("1", 5000, new MyDate("2004", "6", "18"));
        Employee employee2 = new Employee("1", 6000, new MyDate("2010", "6", "18"));
        Employee employee3 = new Employee("1", 7000, new MyDate("2005", "6", "18"));

        List<Employee> array = new ArrayList<>();
        array.add(employee1);
        array.add(employee2);
        array.add(employee3);

        Collections.sort(array, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {

                if (o1.getName().compareTo(o2.getName()) != 0) {
                    return o1.getName().compareTo(o2.getName());
                }

                return o1.getBirthday().compareTo(o2.getBirthday());
            }
        });
        System.out.println(array);
    }
}
class Employee {
    private String name;
    private double sal;
    private MyDate birthday;

    public  Employee(String name, double sal, MyDate birthday) {
        this.name = name;
        this.sal = sal;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", sal=" + sal +
                ", birthday=" + birthday +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }
}

class MyDate implements Comparable<MyDate>{
    private String year;
    private String month;
    private String day;

    public MyDate(String year, String month, String day) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    @Override
    public int compareTo(MyDate o1) {
        //不想写，自己领悟
        return 0;
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


}