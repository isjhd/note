package com.hspedu.innerclass;

public class InnerClassExercise02 {
    public static void main(String[] args) {

        Cellphone.alarmClock(new Bell() {
            public void ring() {
                System.out.println("懒猪起床了");
            }
        });

        Cellphone.alarmClock(new Bell() {
            public void ring() {
                System.out.println("小伙伴上课了");
            }
        });

    }
}
interface Bell {
    void ring();
}

class Cellphone {

    public static void alarmClock(Bell bell) {

        bell.ring();
    }

}