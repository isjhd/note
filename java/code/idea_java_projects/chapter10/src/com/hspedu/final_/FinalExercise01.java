package com.hspedu.final_;

public class FinalExercise01 {
    public static void main(String[] args) {

        new Circle(5).ruan();
    }
}
class Circle {
    private double radius;

    private final double PI;// = 3.14;

    public Circle(double radius) {
        this.radius = radius;
        //PI = 3.14;
    }

    {
        PI = 3.14;
    }

    public void ruan() {
        System.out.println("圆形的面积为" + PI*radius*radius);
    }

}