package com.hspedu.homework;

public class Homework07 {
    public static void main(String[] args) {

        Doctor doctor1 = new Doctor("秋山澪", 18, "弹贝斯", '女', 5000);
        Doctor doctor2 = new Doctor("秋山澪", 18, "弹贝斯", '女', 5000);
        System.out.println(doctor1.equals(doctor2));
    }
}
class Doctor {
    private String name;
    private int age;
    private String job;
    private char gender;
    private double sal;

    public Doctor(String name, int age, String job, char gender, double sal) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.gender = gender;
        this.sal = sal;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Doctor)) {
            return false;
        }

        Doctor doctor2 = (Doctor)obj;
        if (this.name.equals(doctor2.getName()) && this.age == doctor2.getAge() && this.job.equals(doctor2.getJob())
                && this.gender == doctor2.getGender() && this.sal == doctor2.getSal()) {
            return true;
        }
        return false;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }
}