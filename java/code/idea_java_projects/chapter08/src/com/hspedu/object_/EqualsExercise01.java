package com.hspedu.object_;

public class EqualsExercise01 {
    public static void main(String[] args) {

        Person person = new Person("秋山澪", 18, '女');
        Jack jack = new Jack("秋山澪", 18, '女');
        System.out.println(person.equals(jack));


    }
}
class Person {

    private String name;
    private int age;
    private char gender;



    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Person) {
            Person p = (Person)obj;
            return this.name.equals(p.name) && this.age == p.age && this.gender == p.gender;
        }

        return false;
    }

    public Person(String name, int age, char gender) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}

class Jack extends Person{

    public Jack(String name, int age, char gender) {
        super(name, age, gender);
    }
}