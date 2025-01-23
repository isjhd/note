package com.hspedu.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* @author  i-s-j-h-d
 * @version 1.0
 * 演示非捕获分组，语法比较奇怪
 */
public class RegExp08 {
    public static void main(String[] args) {

        String content = "hello轻音少女 jack轻音少妇 重音少女hello";

        //找到 轻音少女、轻音少妇、重音少女 子字符串
        //String regStr = "轻音少女|轻音少妇|重音少女";
        //上面的写法可以等价非捕获分组，注意：不能 matcher.group(1)
        //String regStr = "(?:轻|重)音少(?:女|妇)";

        //找到 轻音少 这个关键字，但是要求只是查找轻音少女、轻音少妇 中含有的轻音少
        //下面也是非捕获分组，不能使用matcher.group(1)
        //String regStr = "轻音少(?=女|妇)";

        //找到 轻音 这个关键字，但是要求只是查找不是 (轻音少女 和 轻音少妇) 中含有的轻音
        //下面也是非捕获分组，不能使用matcher.group(1)
        String regStr = "轻音(?!少女|少妇)";

        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            System.out.println("找到：" + matcher.group(0));
        }


    }
}
