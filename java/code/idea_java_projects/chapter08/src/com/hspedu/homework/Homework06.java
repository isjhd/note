package com.hspedu.homework;

public class Homework06 {
    public static void main(String[] args) {

        LabeledPoint labeledPoint = new LabeledPoint("Black", 1929, 230.07);
        System.out.println(labeledPoint.toString());
    }
}
class LabeledPoint extends Point {
    private String name;

    public LabeledPoint(String name, int x, double y) {
        super(x, y);
        this.name = name;
    }

    @Override
    public String toString() {
        return "name=" + name + super.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Point {
    private int x;
    private double y;

    public Point(int x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return ", x=" + x +
                ", y=" + y +
                '。';
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}