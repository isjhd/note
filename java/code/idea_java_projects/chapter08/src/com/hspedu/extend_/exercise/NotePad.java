package com.hspedu.extend_.exercise;

public class NotePad extends Computer{

    private String color;

    public NotePad(String cpu, int memory, int disk, String color) {
        super(cpu, memory, disk);
        this.setColor(color);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String NotePadDetails () {
        return getDetails() + "，颜色：" + color;
    }
}
