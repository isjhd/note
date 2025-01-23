package com.hspedu.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* @author  i-s-j-h-d
 * @version 1.0
 * 演示正则表达式的使用
 */
public class RegExp11 {
    public static void main(String[] args) {

        String content = "https://www.bilibili.com/video/BV1XN411B7bg/?spm_id_from=333.1007.top_right_bar_window_default_collection.content.click&vd_source=1e76686fcb5720fb01a6d29cfcf015c5";

        /*
            思路
            1. 先确定 url 的开始部分 https:// | http://
            2. 然后通过 ([\\w-]+\\.)+[\\w-] 匹配 www.bilibili.com
            3. video/BV1XN411B7bg/?spm_id_from 匹配(\\/[\\w-?=&/%.#]*)?
        */
        String regStr = "^((http|https)://)?([\\w-]+\\.)+[\\w-]+(\\/[\\w-?=&/%.#]*)?$";

        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        if(matcher.find()) {
            System.out.println("满足格式");
        } else {
            System.out.println("不满足格式");
        }
    }
}
