package com.hspedu.homework;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class homework01 {
    public static void main(String[] args) {

        Newspaper n1 = new Newspaper("新闻一：新冠确诊病例超干万，数百万印度教信徒赴恒河“圣浴”引民众担忧");
        Newspaper n2 = new Newspaper("新闻二：男子突然想起2个月前钓的鱼还在网兜里，捞起一看赶紧放生");

        List newspaper = new ArrayList();
        newspaper.add(n1);
        newspaper.add(n2);

        String newspaper1 = n1.getTitle().substring(15);
        n1.setTitle(n1.getTitle().replace(newspaper1, "..."));

        String newspaper2 = n2.getTitle().substring(15);
        n2.setTitle(n2.getTitle().replace(newspaper2, "..."));
        Collections.reverse(newspaper);
        System.out.println(newspaper);
    }
}
class Newspaper {
    private String title;
    private String content;

    public Newspaper(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "title=" + title + "\n";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}