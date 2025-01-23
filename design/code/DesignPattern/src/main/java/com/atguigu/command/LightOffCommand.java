package com.atguigu.command;

public class LightOffCommand implements Command{

    //聚合LightReceiver
    LightReceiver light;

    //构造器
    public LightOffCommand(LightReceiver light) {
        this.light = light;
    }

    @Override
    public void execute() {
        //调用接受者的方法
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
