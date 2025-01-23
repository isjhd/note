package com.hspedu.homework;

public class Homework01 {
    public static void main(String[] args) {

        Person [] person = new Person[3];
        Person p1 = new Person("平泽唯", 18, "吉他手");
        Person p2 = new Person("阿梓喵", 17, "吉他手");
        Person p3 = new Person("撒哇酱", 28, "老师");
        Person p = new Person();
        p.biJia(person);//冒泡排序

        for (int i = 0; i < person.length; i++) {
            System.out.println(person[i].info());
        }
    }
}
class Person {

    private String name;
    private int age;
    private String job;

    int temp = 0;

    public Person(String name, int age, String job) {
        this.setName(name);
        this.setAge(age);
        this.setJob(job);
    }

    public Person() {

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

    public Person[] biJia(Person[] person) {
        for (int i = 1; i < person.length; i++) {
            for (int j = 0; j < person.length - i; j++) {
                if (person[j].getAge() > person[j + 1].getAge()) {
                    temp = person[j + 1].getAge();
                    person[j + 1].setAge(person[j].getAge());
                    person[j].setAge(temp);
                }
            }
        }
        return person;
    }

    public String info() {
        return "name=" + name + "，age=" + age + "，job=" + job + "。";
    }


}