package com.hspedu.list_;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListExercise2 {
    public static void main(String[] args) {

        List list = new ArrayList();
        list.add(new Book("碧蓝之海", 32, "井上坚二"));
        list.add(new Book("轻音少女", 23, "kakifly"));
        list.add(new Book("春物", 18, "渡航"));

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("======================================");
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {

                Book book1 = (Book)list.get(j);
                Book book2 = (Book)list.get(j + 1);

                if(book1.getMoney() > book2.getMoney()) {
                    list.set(j, book2);
                    list.set(j + 1, book1);
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
class Book {
    private String name;
    private int money;
    private String author;

    public Book(String name, int money, String author) {
        this.name = name;
        this.money = money;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", author='" + author + '\'' +
                '}';
    }
}
