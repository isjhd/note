package com.hspedu.homework;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class homework03 {
    public static void main(String[] args) {

        Map map = new HashMap();
        map.put("jack", 650);
        map.put("tom", 1200);
        map.put("smith", 2900);
        System.out.println(map);

        map.put("jack", 2600);
        System.out.println(map);

        up(map);
        System.out.println(map);

        Set entry = map.entrySet();
        for(Object zen : entry) {
            Map.Entry m = (Map.Entry)zen;
            System.out.println(m.getKey() + "-" + m.getValue());
        }
    }

    public static void up(Map map) {
        Set entry = map.entrySet();
        for(Object zen : entry) {
            Map.Entry m = (Map.Entry)zen;
            map.put(m.getKey(), (int)m.getValue() + 100);
        }
    }
}
