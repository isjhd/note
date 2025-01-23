package com.hspedu.homework;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

public class Homework05 {
    public static void main(String[] args) {

        new A().b();
    }
}
class A {

    private final String name = "沙优酱";

    public void b() {
        class B {
            private final String name = "阿梓喵";

            public void show() {
                System.out.println("内部" + name);
                System.out.println("外部" + A.this.name);
            }
        }

        B b = new B();
        b.show();
    }


}