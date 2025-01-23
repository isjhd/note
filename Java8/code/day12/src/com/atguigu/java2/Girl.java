package com.atguigu.java2;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class Girl {

    private String name;

    public Girl() {
    }

    public Girl(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Girl{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
