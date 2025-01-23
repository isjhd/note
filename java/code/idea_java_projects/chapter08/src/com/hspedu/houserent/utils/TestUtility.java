package com.hspedu.houserent.utils;

public class TestUtility {
    public static void main(String[] args) {

        //这是一个测试类，使用完毕就可以删除了

        System.out.println("请输入一个字符串：");
        String s = Utility.readString(3);
        //要求输入一个字符串，长度最大为3
        System.out.println("s=" + s);
    }
}
