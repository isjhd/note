package com.hspedu.enum_;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class Enumeration02 {
    public static void main(String[] args) {
        System.out.println(Season.SPRING);
    }
}
class Season {
    private String name;
    private String desc;

    public static Season SPRING = new Season("春天", "温暖");

    private Season(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}