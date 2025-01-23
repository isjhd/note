package com.hspedu.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* @author  i-s-j-h-d
 * @version 1.0
 * 演示字符匹配符 的使用
 */
public class RegExp03 {
    public static void main(String[] args) {
        String content = "a11c8 abc_ABC y@";
        //String regStr = "[abcd]";//匹配在 abcd 之间的任意一个字符
        //String regStr = "\\D";//匹配 不在 0-9的任意一个字符
        //String regStr = "\\w";//匹配 大小写英文字母，数字，下划线
        //String regStr = "\\W";//匹配 等价于 [^a-zA-Z0-9_]
        //String regStr = "\\s";// \\s 匹配任何空白字符(空格，制表符等)
        //String regStr = "\\S";// \\S 匹配任何非空白字符，和\\s刚好相反
        String regStr = ".";// . 匹配除 \n 之外的所有字符，如果要匹配 . 本身则需要使用\\.


        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            System.out.println("找到：" + matcher.group(0));
        }
    }
}
