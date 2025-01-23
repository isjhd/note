package com.atguigu;

public class Test {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("0123456789");
        String substring = stringBuilder.substring(0, 5);
        String substring2 = stringBuilder.substring(5, 6);
        System.out.println(substring);
        System.out.println(substring2);
    }
}
