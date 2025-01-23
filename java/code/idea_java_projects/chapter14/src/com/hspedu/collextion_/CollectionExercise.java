package com.hspedu.collextion_;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionExercise {
    public static void main(String[] args) {

        List list = new ArrayList();
        list.add(new Dog("小黄", 18));
        list.add(new Dog("小红", 16));
        list.add(new Dog("小蓝", 10));

        Iterator iterator = list.iterator();
        while(iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(next);
        }

        System.out.println("=====================");

        for(Object call : list) {
            System.out.println(call);
        }
    }
}
class Dog {
    private String name;
    private int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}