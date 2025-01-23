package com.hspedu.set_;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class LinkedHashSetExercise {
    public static void main(String[] args) {

        Set linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(new Car("奥拓", 10000));
        linkedHashSet.add(new Car("奥迪", 50000));
        linkedHashSet.add(new Car("奥拓", 10000));
        System.out.println(linkedHashSet);
    }
}
class Car {
    private String name;
    private double price;

    public Car(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.getPrice(), getPrice()) == 0 && Objects.equals(getName(), car.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice());
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}