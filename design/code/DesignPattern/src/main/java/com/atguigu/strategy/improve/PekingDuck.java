package com.atguigu.strategy.improve;

public class PekingDuck extends Duck {

    //构造器，传入FlyBehavior的对象
    public PekingDuck() {
        flyBehavior = new BadFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println("这是北京鸭");
    }


}
