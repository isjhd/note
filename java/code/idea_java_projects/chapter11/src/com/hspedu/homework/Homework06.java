package com.hspedu.homework;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

public class Homework06 {
    public static void main(String[] args) {

        Person person = new Person("唐僧", new Horse());
        person.passRiver();
        person.common();
        person.passRiver();
        person.passRiver();
        person.common();
    }
}
interface Vehicles {
    public void work();
}

class Horse implements Vehicles{
    public void work() {
        System.out.println("Vehicles出行");
    }
}

class Boat implements Vehicles{
    public void work() {
        System.out.println("Boat出行");
    }
}

class Person {
    private String name;
    private Vehicles vehicles;

    public Person(String name , Vehicles vehicles) {
        this.name = name;
        this.vehicles = vehicles;
    }

    public void passRiver() {
        if (!(vehicles instanceof Horse)) {
            vehicles = VehiclesFactory.getHorse();
        }
        vehicles.work();
    }

    public void common() {
        if (!(vehicles instanceof Boat)) {
            vehicles = VehiclesFactory.getBoat();
        }
        vehicles.work();
    }
}

class VehiclesFactory {

    private static Horse horse = new Horse();

    private VehiclesFactory() {

    }

    public static Horse getHorse() {
        return horse;
    }

    public static Boat getBoat() {
        return new Boat();
    }

}