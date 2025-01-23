package com.hspedu.homework;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

public class Homework07 {
    public static void main(String[] args) {

        Car car = new Car(41);
        Car.Air car1 = car.getAir();
        car1.flow();

        Car car3 = new Car(-1);
        Car.Air car4 = car3.getAir();
        car4.flow();
    }
}
class Car {
    private double temperature;

    public Car(double temperature) {
        this.temperature = temperature;
    }

    class Air {
        public void flow() {
            if (temperature >= 40) {
                System.out.println("吹冷风");
            } else if (temperature <= 0) {
                System.out.println("吹热风");
            } else {
                System.out.println("温度正常");
            }
        }
    }

    public Air getAir() {
        return new Air();
    }
}