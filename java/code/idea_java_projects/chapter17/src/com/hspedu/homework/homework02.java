package com.hspedu.homework;

/*
 * @author  i-s-j-h-d
 * @version 1.0
 */
public class homework02 {
    public static void main(String[] args) {

        T1 t1 = new T1();
        Thread t2 = new Thread(t1);
        Thread t3 = new Thread(t1);
        t2.start();
        t3.start();
    }
}
class T1 implements Runnable{

    private static int money = 10000;
    private boolean loop = true;

    public void run() {
            while (loop) {
                synchronized (this) {
                    if (money < 1000) {
                        break;
                    }
                    money -= 1000;
                }
                System.out.println("还剩" + money + "钱");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
    }
}
