package com.hspedu.homework;

public class Homework05 {
    public static void main(String[] args) {

        SavingsAccount savingsAccount= new SavingsAccount(1000);
        savingsAccount.deposit(100);
        savingsAccount.deposit(100);
        savingsAccount.deposit(100);
        savingsAccount.earnMonthlyInterest();
        savingsAccount.deposit(100);
        System.out.println(savingsAccount.getBalance());
    }
}
class SavingsAccount extends BankAccount{
    private double interest = 0.01;
    private int count = 3;
    public SavingsAccount(double initialBalance) {
        super(initialBalance);
    }

    public void earnMonthlyInterest() {
        setBalance(getBalance() + getBalance()*interest);
        count = 3;
    }

    public void deposit(double amount) {
        if (count > 0) {
            super.deposit(amount);
        } else {
            super.deposit(amount - 1);
        }
        count--;
    }

    public void withdraw(double amount){
        if (count > 0) {
            super.withdraw(amount);
        } else {
            super.withdraw(amount + 1);
        }
        count--;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

class CheckingAccount extends BankAccount{//父类
    public CheckingAccount(double initialBalance) {
        super(initialBalance);
    }

    public void deposit(double amount) {
        super.deposit(amount - 1);
    }

    public void withdraw(double amount){
        super.withdraw(amount + 1);
    }
}

class BankAccount {//爷爷类
    private double balance;//余额
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {//存款
        balance += amount;
    }

    public void withdraw(double amount) {//取款
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}