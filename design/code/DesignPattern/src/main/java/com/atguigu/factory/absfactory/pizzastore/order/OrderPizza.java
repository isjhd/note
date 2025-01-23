package com.atguigu.factory.absfactory.pizzastore.order;

import com.atguigu.factory.absfactory.pizzastore.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OrderPizza {

    AbsFactory factory;

    //构造器
    public OrderPizza(AbsFactory factory) {
        setFactory(factory);
    }

    private void setFactory(AbsFactory factory) {
        System.out.println("~使用的是抽象工厂模式~");
        Pizza pizza = null;
        String orderType = ""; //用户输入
        this.factory = factory;
        do {
            orderType = getType();
            pizza = factory.createPizza(orderType);
            if(pizza != null) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
                System.out.println("订购失败");
            }
        } while(true);
    }

    //写一个方法，可以获取客户希望订购的披萨种类
    private String getType() {
        String str;
        try {
            BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input pizza 种类：");
            str = strin.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return str;
    }

}
