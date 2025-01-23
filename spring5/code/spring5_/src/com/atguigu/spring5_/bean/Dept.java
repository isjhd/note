package com.atguigu.spring5_.bean;

/* @author  i-s-j-h-d
 * @version 1.0
 * 部门类
 * */
public class Dept {
    private String dname;

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "dname='" + dname + '\'' +
                '}';
    }
}
