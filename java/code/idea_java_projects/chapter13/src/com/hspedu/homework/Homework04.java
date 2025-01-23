package com.hspedu.homework;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

public class Homework04 {
    public static void main(String[] args) {

        countStr("f3h4985th98fj34");
    }

    public static void countStr(String str) {

        if(str == null) {
            System.out.println("输入不能为null");
        }

        char [] chars = str.toCharArray();
        int large = 0;
        int small = 0;
        int number = 0;
        for(int i = 0; i < chars.length; i++) {
            if(chars[i] >= 'A' && chars[i] <= 'Z') {
                large++;
            } else if(chars[i] >= 'a' && chars[i] <= 'z') {
                small++;
            } else if(chars[i] >= '0' && chars[i] <= '9') {
                number++;
            }
        }

        System.out.println("大写的字母有：" + large + "小写的字母有：" + small + "数字有：" + number);
    }
}
