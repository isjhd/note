package com.isjhd.test;


import java.util.ArrayList;
import java.util.List;


public class test {

    public static void main(String[] args) {
        alloc();
    }

    private static void alloc() {
        Point point = new Point(1, 2);
        System.out.println("point.x= " + point.x + "; point.y= " + point.y);
    }

}

class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


