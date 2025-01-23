package com.hspedu.homework;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

public class Homework03 {
    public static void main(String[] args) {

        String s = "Han shun Ping";
        print(s);
    }

    public static void print(String name) {
        if (name == null) {
            System.out.println("不能为空");
            return;
        }

        String [] names = name.split(" ");

        if (names.length != 3) {
            System.out.println("字符串格式不正确");
            return;
        }

        System.out.println(String.format("%s,%s.%c",names[2],names[0],names[1].toUpperCase().charAt(0)));

    }
}
