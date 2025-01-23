package com.hspedu.homework;

public class Homework02 {
    public static void main(String[] args) {

    Professor professor = new Professor("撒哇酱", 28, "教吉他", 3000, 1.3);
        System.out.println(professor.introduce());
    }
}
class Professor extends Teacher {
    public Professor(String name, int age, String post, double salary, double grade) {
        super(name, age, post, salary, grade);
    }
        public String introduce() {
            return super.introduce();
        }
}

class AssociateProfessor extends Teacher{
    public AssociateProfessor(String name, int age, String post, double salary, double grade) {
        super(name, age, post, salary, grade);
    }

    public String introduce() {
        return super.introduce();
    }
}

class Lecturer extends Teacher{
    public Lecturer(String name, int age, String post, double salary, double grade) {
        super(name, age, post, salary, grade);
    }

    public String introduce() {
        return super.introduce();
    }
}

class Teacher {
    private String name;
    private int age;
    private String post;
    private double salary;

    private double grade;

    public Teacher(String name, int age, String post, double salary, double grade) {
        this.name = name;
        this.age = age;
        this.post = post;
        this.salary = salary;
        this.grade = grade;
    }

    public String introduce() {
        return "name=" + name + "，age=" + age
                + "，post" + post + "，salary=" + salary + "，grade=" + grade;
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

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}