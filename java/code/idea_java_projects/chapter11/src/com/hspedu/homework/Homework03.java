package com.hspedu.homework;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

public class Homework03 {
    public static void main(String[] args) {

        Animal dog = new Dog();
        dog.shout();
        Animal cat = new Cat();
        cat.shout();
    }
}
abstract class Animal {
    public abstract void shout();

}

class Dog extends Animal {
    public void shout() {
        System.out.println("小狗");
    }
}

class Cat extends Animal {
    public void shout() {
        System.out.println("小猫");
    }
}