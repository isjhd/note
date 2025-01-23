package com.hspedu.list_;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.List;

public class ListExercise {
    public static void main(String[] args) {

        List list = new ArrayList();
        list.add("jack");
        list.add("tom");
        list.add("appo");
        list.add("bob");
        list.add("april");
        list.add("march");
        list.add("may");
        list.add("wj");
        System.out.println(list);

        list.add(1, "韩顺平");
        System.out.println(list);

        list.get(4);
        System.out.println(list);

        list.remove(5);
        System.out.println(list);

        list.set(6, "玛丽");
        System.out.println(list);
    }
}

