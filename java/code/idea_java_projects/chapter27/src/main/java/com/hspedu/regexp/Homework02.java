package com.hspedu.regexp;


/* @author  i-s-j-h-d
 * @version 1.0 */
public class Homework02 {
    public static void main(String[] args) {
        /*
            思路：
            1. 先写出简单的正则表达式
            2. 在逐步的完善【根据各种情况来完善】
        */
        String content = "-07.8";
        String regStr = "^[-+]?([1-9]\\d*|0)(\\.\\d+)?$";

        if(content.matches(regStr)) {
            System.out.println("匹配成功 是整数或者小数");
        } else {
            System.out.println("匹配失败");
        }

    }
}
