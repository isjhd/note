package com.hspedu.map_;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapExercise {
    public static void main(String[] args) {

        Map map = new HashMap();
        map.put("0001", new Employee("wj", 15000, "0001"));
        map.put("0002", new Employee("唯", 18000, "0002"));
        map.put("0003", new Employee("优", 19000, "0003"));

        Set keyset = map.keySet();
        for(Object key : keyset) {
            Object obj = map.get(key);
            Employee value = (Employee)obj;
            if(value.getMoney() >= 18000) {
                System.out.println(key + "-" + obj);
            }
        }
    }
}
class Employee {
    private String name;
    private double money;
    private String ID;

    public Employee(String name, double money, String ID) {
        this.name = name;
        this.money = money;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", ID='" + ID + '\'' +
                '}';
    }
}