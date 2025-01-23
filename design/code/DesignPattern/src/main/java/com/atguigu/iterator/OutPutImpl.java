package com.atguigu.iterator;

import java.util.Iterator;
import java.util.List;

public class OutPutImpl {

    //学院集合
    List<College> collegeList;

    public OutPutImpl(List<College> collegeList) {
        this.collegeList = collegeList;
    }

    //遍历所有学院，然后调用printDepartment 输出各个学院的系
    public void printCollege() {

        // 从collegeList 取出所有学院，java中的list已经实现Iterator
        Iterator<College> iterator = collegeList.iterator();

        while(iterator.hasNext()) {
            //取出一个学院
            College college = iterator.next();
            System.out.println("======= "+ college.getName() +" =============");
            printDepartment(college.createIterator()); //得到对应的迭代器
        }

    }

    //输出 学院 输出 系
    public void printDepartment(Iterator iterator) {
        while(iterator.hasNext()) {
            Department d = (Department) iterator.next();
            System.out.println(d.getName());
        }
    }

}
