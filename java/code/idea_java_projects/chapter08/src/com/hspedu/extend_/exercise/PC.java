package com.hspedu.extend_.exercise;

public class PC extends Computer{

    private String brand;

    public PC(String cpu, int memory, int disk, String brand) {
        super(cpu, memory, disk);
        this.setBrand(brand);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String PCDetails () {
        return getDetails() + "，品牌：" + brand;
    }
}
