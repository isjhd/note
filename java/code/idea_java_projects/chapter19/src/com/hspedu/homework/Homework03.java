package com.hspedu.homework;


import org.junit.Test;

import java.io.*;
import java.util.Properties;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class Homework03 {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("name", "tom");
        properties.setProperty("age", "5");
        properties.setProperty("color", "red");
        properties.store(new FileOutputStream("src\\mysql3.properties"), null);

        Dog1 dog1 = new Dog1(properties.getProperty("name"), properties.getProperty("age"), properties.getProperty("color"));
        System.out.println(dog1);

        String filePath = "D:\\Dag.dat";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        oos.writeObject(dog1);
        oos.close();
    }

    @Test
    public void m1() throws IOException, ClassNotFoundException {
        String filePath = "D:\\Dag.dat";
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        Object dog = ois.readObject();
        Dog1 dog1 = (Dog1)dog;
        System.out.println(dog1);
        ois.close();
    }

}
class Dog1 implements Serializable {
    String name;
    String age;
    String color;

    public Dog1(String name, String age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}