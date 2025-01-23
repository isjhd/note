package com.hspedu.encap;

public class AccountTest {
    public static void main(String[] args) {

        Account account = new Account();
        account.setName("秋山澪是蓝色的");
        account.setBalance(10000);
        account.setPassword("098765");
        System.out.println("秋山澪的信息如下：");
        System.out.println(account.info());

    }
}
class Account {
    private String name;
    private double balance;
    private String password;

    public Account () {

    }

    public Account (String name, double balance, String password) {
        this.setName(name);
        this.setBalance(balance);
        this.setPassword(password);
    }

    public void setName (String name) {
        if (name.length() >= 2 && name.length() <= 4) {
            this.name = name;
        } else {
            System.out.println("姓名的长度必须在2~4位");
            this.name = "平泽唯";
        }
    }

    public String getName () {
        return name;
    }

    public void setBalance (double balance) {
        if (balance >= 20) {
            this.balance = balance;
        } else {
            System.out.println("余额必须大于20元");
            this.balance = 20.0;
        }
    }

    public double getBalance () {
        return balance;
    }

    public void setPassword (String password) {
        if (password.length() == 6) {
            this.password = password;
        } else {
            System.out.println("密码必须是6位数");
            this.password = "123456";
        }
    }

    public String getPassword () {
        return password;
    }

    public String info () {
        return "姓名是：" + name + "，余额为" + balance + "，密码是" + password;
    }

}
