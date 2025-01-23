package com.atguigu.prototype;

public class Client {

    public static void main(String[] args) {
        //传统的方法
        Sheep sheep = new Sheep("tom", 1, "白色");

        Sheep sheep2 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
    }

}
