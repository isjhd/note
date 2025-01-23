package com.hspedu.set_;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

import java.util.HashSet;
import java.util.Objects;

public class HashSetExercise02 {
    public static void main(String[] args) {

        HashSet hashSet = new HashSet();
        MyDate myDate1 = new MyDate(2004, 11, 27);
        MyDate myDate2 = new MyDate(2004, 11, 27);
        hashSet.add(new Employee2("唯", 8000, myDate1));
        hashSet.add(new Employee2("唯", 8000, myDate2));
        System.out.println(hashSet);
    }
}
class Employee2 {
    private String name;
    private double sal;
    private MyDate birthday;

    public Employee2(String name, double sal, MyDate birthday) {
        this.name = name;
        this.sal = sal;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee2 employee2 = (Employee2) o;
        return Double.compare(employee2.sal, sal) == 0 && Objects.equals(name, employee2.name) && Objects.equals(birthday, employee2.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sal, birthday);
    }

    @Override
    public String toString() {
        return "Employee2{" +
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

class MyDate {
    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyDate myDate = (MyDate) o;
        return year == myDate.year && month == myDate.month && day == myDate.day;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}