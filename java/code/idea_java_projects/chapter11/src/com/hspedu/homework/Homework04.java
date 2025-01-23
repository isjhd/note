package com.hspedu.homework;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

public class Homework04 {
    public static void main(String[] args) {

        Cellphone cellphone = new Cellphone();
        cellphone.testWork(new ICalculate(){
            public int work(int a , int b) {
                return a + b;
            }
        } ,3 ,4 );
    }
}
interface ICalculate {
    public int work(int a, int b);
}

class Cellphone{
    public void testWork(ICalculate icalculate, int a, int b) {
        System.out.println(icalculate.work(a, b));
    }
}