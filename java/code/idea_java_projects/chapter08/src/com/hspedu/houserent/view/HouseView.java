package com.hspedu.houserent.view;

import com.hspedu.houserent.domain.House;
import com.hspedu.houserent.service.HouseService;
import com.hspedu.houserent.utils.Utility;

/*
* 1、显示界面
* 2、接受用户的输入
* 3、调用HouseService完成对房屋信息的各种操作
**/
public class HouseView {

    private boolean loop = true;//控制显示菜单
    private char key;//接受用户选择
    private HouseService houseService = new HouseService(10);//设置数组的大小为1

    //修改房源
    public void updateHouse() {
        System.out.println("========================修改客户============================");
        System.out.print("请选择待修改房屋编号(-1退出):");
        int findId = Utility.readInt();
        if (findId == -1) {
            System.out.println("退出修改");
            return;
        }
        House houses = houseService.find(findId);
        if (houses == null) {
            System.out.println("=======================没有该房屋=======================");
            return;
        }else {
            System.out.print("姓名(" + houses.getName() + ")：");
            String name = Utility.readString(12, "");
            if(!"".equals(name)) {
                houses.setName(name);
            }
            System.out.print("电话(" + houses.getPhone() + ")：");
            String phone = Utility.readString(12, "");
            if(!"".equals(phone)) {
                houses.setPhone(phone);
            }
            System.out.print("地址(" + houses.getAddress() + ")：");
            String address = Utility.readString(12, "");
            if(!"".equals(address)) {
                houses.setAddress(address);
            }
            System.out.print("月租(" + houses.getRent() + ")：");
            int rent = Utility.readInt(-1);
            if(rent != -1) {
                houses.setRent(rent);
            }
            System.out.print("状态(" + houses.getState() + ")：");
            String state = Utility.readString(12, "");
            if(!"".equals(state)) {
                houses.setState(state);
            }
            System.out.println("========================修改完成===========================");
        }
    }
    //查找房屋，
    public void findHouse() {
        System.out.println("=========================查找房屋=========================");
        System.out.print("请输入你要查找id：");
        int findId = Utility.readInt();
        if (houseService.find(findId) == null) {
            System.out.println("=======================没有该房屋=======================");
        }else {
            System.out.println(houseService.find(findId));
        }
    }

    //完成退出确认
    public void exit() {
        //这里使用Utility提供的方法，完成确认
        char c = Utility.readConfirmSelection();
        if(c == 'Y') {
            loop =false;
        }
    }

    //编写delHouse() 接收输入的id，调用Service的del方法
    public void delHouse() {
        System.out.println("==================删除房屋=========================");
        System.out.print("请选择待删除房屋编号(-1退出：)");
        int delId = Utility.readInt();
        if (delId == -1) {
            System.out.println("==================放弃删除房屋信息=====================");
            return;
        }
        System.out.println("确认是否删除(Y/N)：请小心选择：");
        char choice = Utility.readConfirmSelection();//必须输出Y/N
        if (choice == 'Y') {
            if (houseService.del(delId)) {
                System.out.println("删除房屋信息成功...");
            } else {
                System.out.println("房屋编号不存在，删除失败...");
            }
        }else {
            System.out.println("==================放弃删除房屋信息=====================");
        }
    }
    //便携addHouse() 接收输入，创建House对象，调用add方法
    public void addHouse() {
        System.out.println("===================添加房屋=======================");
        System.out.print("姓名：");
        String name = Utility.readString(8);
        System.out.print("电话：");
        String phone = Utility.readString(12);
        System.out.print("地址：");
        String address = Utility.readString(16);
        System.out.print("月租：");
        int rent = Utility.readInt();
        System.out.print("状态(已出租/未出租)：");
        String state = Utility.readString(3);
        House newHouse = new House(0, name, phone, address, rent, state);
        if (houseService.increase(newHouse)) {
            System.out.println("======================添加房屋成功=====================");
        } else {
            System.out.println("添加房屋失败...");
        }

    }

    //编写listHouses()显示房屋列表 =
    public void listHouses() {
        System.out.println("==================房屋列表=======================");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态(未出租/已出租)");
        House[] houses = houseService.list();//得到所有房屋信息
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == null) {
                break;
            }
            System.out.println(houses[i]);
        }
        System.out.println("============房屋列表显示完毕======================");
    }
    //显示主菜单
    public void mainMenu() {
        do {
            System.out.println("\n==============房屋出租系统菜单===============");
            System.out.println("\t\t\t1、新增房源");
            System.out.println("\t\t\t2、查找房屋");
            System.out.println("\t\t\t3、删除房屋");
            System.out.println("\t\t\t4、修改房屋信息");
            System.out.println("\t\t\t5、房屋列表");
            System.out.println("\t\t\t6、退   出");

            System.out.print("请输入您的选择(1~6): ");
            key = Utility.readChar();
            switch (key) {
                case '1':
                    addHouse();
                    break;
                case '2':
                    findHouse();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    updateHouse();
                    break;
                case '5':
                    listHouses();
                    break;
                case '6':
                    exit();
                    break;
            }
        } while(loop);
        System.out.println("你退出了房屋出租系统...");
    }
}
