package com.hspedu.enum_;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

public class EnumExercise02 {
    public static void main(String[] args) {

        Week [] weeks = Week.values();

        for (Week week : weeks) {
            System.out.println(week);
        }
    }
}
enum Week {
    MONDAY("星期一"),TUESDAY("星期二"),WEDNESDAY("星期三"),THURSDAY("星期四"),FRIDAY("星期五"),SATURDAY("星期六"),SUNDAY("星期日");

    private String week;

    private Week(String week) {
        this.week = week;
    }

    public String getName() {
        return week;
    }

    @Override
    public String toString() {
        return getName();
    }
}

