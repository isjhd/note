package com.hspedu.regexp;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class StringReg {
    public static void main(String[] args) {
        String content = "hello#abc-jack12smith~北京";

        //要求按照 # 或者 - 或者 ~ 或者 数字 来分割
        String[] split = content.split("#|-|~|\\d+");
        for(String s : split) {
            System.out.println(s);
        }
    }
}
