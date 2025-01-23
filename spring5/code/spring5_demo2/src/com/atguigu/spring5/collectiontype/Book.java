package com.atguigu.spring5.collectiontype;

import java.util.List;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class Book {
    private List<String> list;

    public void setList(List<String> list) {
        this.list = list;
    }

    public void test() {
        System.out.println(list);
    }
}
