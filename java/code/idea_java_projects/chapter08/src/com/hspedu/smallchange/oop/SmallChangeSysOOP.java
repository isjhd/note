package com.hspedu.smallchange.oop;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SmallChangeSysOOP {

    Scanner scanner = new Scanner(System.in);
    String key = "";//零钱通菜单输入1~4,展示效果。

    String details = "----------------零钱通明细-----------------";
    double money = 0;//收益入账
    double balance = 200;//余额
    Date date = null;//日期
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");//日期格式化
    String note = "";//消费说明
    boolean loop = true;

    public void mainMenu() {

        do {
            System.out.println("==============零钱通菜单(OOP)================");
            System.out.println("\t\t\t1、零钱通明细");
            System.out.println("\t\t\t2、收益入账");
            System.out.println("\t\t\t3、消费");
            System.out.println("\t\t\t4、退    出");
            System.out.print("请选择(1-4):");

            key = scanner.next();//零钱通菜单输入1~4,展示效果。

            switch (key) {
                case "1":
                    this.detail();
                    break;
                case "2":
                    this.income();
                    break;
                case "3":
                    this.pay();
                    break;
                case "4":
                    this.exit();
                    break;
                default:
                    System.out.println("您输入的信息有误...");
                    break;
            }

        } while (loop);
        System.out.println("退出零钱通项目...");
    }

    public void detail() {
        System.out.println(details);
    }

    public void income() {
        System.out.print("收益入账:");
        money = scanner.nextDouble();//接受一个收益入账

        if (money <= 0) {
            System.out.println("收益入账不能为0或负数");
            return;
        }

        balance += money;//余额
        date = new Date();//日期
        details += "\n收益入账\t+" + money + "\t" + sdf.format(date) + "\t" + "余额=" + balance;
    }

    public void pay() {
        System.out.print("消费金额:");
        money = scanner.nextDouble();//接受一个消费金额

        if (money <= 0 || money > balance) {
            System.out.println("消费金额必须在0~" + balance);
            return;
        }
        System.out.print("消费说明:");
        note = scanner.next();
        balance -= money;//余额
        date = new Date();//日期
        details += "\n" + note + "\t-" + money + "\t" + sdf.format(date) + "\t" + "余额=" + balance;
    }

    public void exit() {
        String choice = "";

        while (true) {
            System.out.println("您确定要退出吗？ y/n");
            choice = scanner.next();

            if (choice.equals("y") || choice.equals("n")) {
                break;
            } else {
                System.out.println("您输入有误，请重新输入...");
            }
        }

        if (choice.equals("y")) {
            loop = false;
            return;
        } else if (choice.equals("n")) {
            return;
        }
    }
}
