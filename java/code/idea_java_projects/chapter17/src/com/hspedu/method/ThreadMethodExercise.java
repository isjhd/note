package com.hspedu.method;

/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

public class ThreadMethodExercise {
    public static void main(String[] args) throws InterruptedException {

        T800 t800 = new T800();

        for(int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("hi" + i);
            if(i == 5) {
                t800.start();
                t800.join();
            }
        }
    }

}
class T800 extends Thread{
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("hello" + i);
        }
    }
}