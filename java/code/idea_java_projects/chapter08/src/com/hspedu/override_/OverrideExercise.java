package com.hspedu.override_;

public class OverrideExercise {
    public static void main(String[] args) {
        Person person = new Person("平泽唯", 18);
        Student student = new Student("平泽优", 16, 520, 80);
        System.out.println(person.say());
        System.out.println(student.say());


    }
}
