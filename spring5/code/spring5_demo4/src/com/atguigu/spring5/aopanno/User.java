package com.atguigu.spring5.aopanno;

import org.springframework.stereotype.Component;

/* @author  i-s-j-h-d
 * @version 1.0 */
//被增强的类
@Component
public class User {
    public void add() {
        System.out.println("add...");
    }
}
