package com.atguigu.builder.improve;

//产品->Product
public class House {
    private String baise;
    private String wall;
    private String roofed;

    public House(String baise, String wall, String roofed) {
        this.baise = baise;
        this.wall = wall;
        this.roofed = roofed;
    }

    public House() {
    }

    @Override
    public String toString() {
        return "House{" +
                "baise='" + baise + '\'' +
                ", wall='" + wall + '\'' +
                ", roofed='" + roofed + '\'' +
                '}';
    }

    public String getBaise() {
        return baise;
    }

    public void setBaise(String baise) {
        this.baise = baise;
    }

    public String getWall() {
        return wall;
    }

    public void setWall(String wall) {
        this.wall = wall;
    }

    public String getRoofed() {
        return roofed;
    }

    public void setRoofed(String roofed) {
        this.roofed = roofed;
    }
}
