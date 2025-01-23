package com.atguigu.java1;

import com.atguigu.java.bean.Employee;

import java.util.ArrayList;
import java.util.List;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class EmployeeData {

    public static List<Employee> getEmployees() {
        List<Employee> list = new ArrayList<>();

        list.add(new Employee("1001","马化腾",34,6000.38));
        list.add(new Employee("1002", "马云",12,9876.12));
        list.add(new Employee("1003","刘强东",33,3880.82));
        list.add(new Employee("1004","雷军",26,7657.37));
        list.add(new Employee("1005","李彦宏",65,5555.32));
        list.add(new Employee("1006","比尔盖茨",42,9508.43));
        list.add(new Employee("1007","任正非",26,4333.32));
        list.add(new Employee("1008","扎克伯格",35,2500.32));

        return list;

    }

}
