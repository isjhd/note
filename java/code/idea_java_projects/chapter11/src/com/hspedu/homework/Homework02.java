package com.hspedu.homework;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

public class Homework02 {
    public static void main(String[] args) {

        Frock f1 = new Frock();
        System.out.println(f1.getNextNum());

        Frock f2 = new Frock();
        System.out.println(f2.getNextNum());

        Frock f3 = new Frock();
        System.out.println(f3.getNextNum());
    }
}
class Frock{
    private static int currentNum = 100000;
    private int serialNumber;

    public Frock() {
        this.serialNumber = getNextNum();
    }

    public static int getNextNum() {
        currentNum = currentNum + 100;
        return currentNum;
    }

    public int getSerialNumber() {
        return serialNumber;
    }


}