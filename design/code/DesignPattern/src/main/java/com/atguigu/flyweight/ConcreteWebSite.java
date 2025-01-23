package com.atguigu.flyweight;

//具体的网站
public class ConcreteWebSite extends WebSite{

    //共享的部分，内部状态
    private String type = ""; //网站发布的形式类型

    public ConcreteWebSite(String type) {
        this.type = type;
    }

    @Override
    public void use(User user) {
        System.out.println("网站的发布形式为：" + type + "在使用中...");
        System.out.println("使用者是：" + user.getName());
    }

}
