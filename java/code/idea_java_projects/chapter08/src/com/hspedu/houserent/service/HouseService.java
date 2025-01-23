package com.hspedu.houserent.service;

import com.hspedu.houserent.domain.House;

/*
* 业务层
* //定义House[]，保存House对象
* 1、响应HouseView的调用
* 2、完成对房屋信息的各种操作(增删改查)
* */
public class HouseService {

    private House[] houses;//保存House对象
    private int size;
    private int houseNums = 1;
    private int idCounter = 1;


    public HouseService(int size) {//构造器
        //(2)数组扩容，动态的增加房屋。
        //size++;
        houses = new House[size];//当创建HouseService对象，指定数组大小
        //为了配合测试列表信息，这里初始化一个House对象
        houses[0] = new House(1, "撒瓦酱", "183", "湖北", 2000, "已出租");
    }

    //findById方法，返回House对象或者null
    public House find(int findId) {
        for (int i = 0; i < houseNums; i++) {
            if (houses[i].getId() == findId) {
                return houses[i];
            }
        }
        return null;
    }

    public boolean del(int delId) {
        //应当先找到要删除的房屋信息对应的下表
        //一定要搞清楚，下表和房屋的编号不是一回事
        int index = -1;
        for (int i = 0; i < houseNums; i++) {
            if(delId == houses[i].getId()) {//要删除的房屋(id)，是数组下标为i的元素
                index = i;//使用index记录i
            }
        }

        if(index == -1) {//说明delId在数组中不存在
            return false;
        }
        //这有点难
        for (int i = index; i < houseNums - 1; i++) {
            houses[i] = houses[i+1];
        }
        houses[--houseNums] = null;

        return true;
    }


    public boolean increase(House newHouse) {//add方法，添加新对象，返回boolean

        //(1)判断是否还可以继续添加(暂时不考虑数组扩容的问题)，只能添加等同于数组大小的房屋数量
        if (houseNums == houses.length) {//不能再添加
            System.out.println("数组已满，不能再添加...");
            return false;
        }
        //把newHouse对象加入到，新增加了一个房屋
        houses[houseNums++] = newHouse;
        //需要设计一个id自增长的机制，然后更新newHouse的id
        newHouse.setId(++idCounter);
        return true;
    }

    public House[] list(){//list方法，返回houses
        return houses;
    }


}
