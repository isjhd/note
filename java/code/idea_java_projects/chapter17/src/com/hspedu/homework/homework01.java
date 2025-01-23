package com.hspedu.homework;

import java.util.Scanner;

/*
 * @author  i-s-j-h-d
 * @version 1.0
 */
public class homework01 {
    public static void main(String[] args) {

        A a = new A();
        B b = new B(a);
        a.start();
        b.start();

    }
}
class A extends Thread{

    private static boolean loop = true;

    public void run() {
        synchronized (this) {
            while (loop) {
                System.out.println(1 + (int)(Math.random()*100));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public boolean getLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}

class B extends Thread{

    private A a;
    private Scanner scanner = new Scanner(System.in);

    public B(A a) {
        this.a = a;
    }

    public void run() {
        while (a.getLoop()) {
            System.out.println("请输入你的指令(Q)表示退出：");
            char key = scanner.next().toUpperCase().charAt(0);
            if(key == 'Q') {
                a.setLoop(false);
                System.out.println("b线程退出...");
            }
        }
    }

}