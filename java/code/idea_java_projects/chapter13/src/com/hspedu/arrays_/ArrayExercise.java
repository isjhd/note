package com.hspedu.arrays_;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

import java.util.Arrays;
import java.util.Comparator;

public class ArrayExercise {
    public static void main(String[] args) {

        Book [] book = new Book[4];
        book[0] = new Book("红楼梦", 100);
        book[1] = new Book("金瓶梅~", 90);
        book[2] = new Book("青年文摘20年", 5);
        book[3] = new Book("java从入门到放弃", 300);

        Arrays.sort(book, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                o1.getName().length();
                return o2.getName().length() - o1.getName().length();
            }
        });

        System.out.println(Arrays.toString(book));
    }
}
class Book {
    private String name;
    private int price;

    public Book(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}