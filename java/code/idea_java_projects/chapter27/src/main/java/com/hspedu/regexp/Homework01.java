package com.hspedu.regexp;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class Homework01 {
    public static void main(String[] args) {
        String content = "3478215435@qq.com";

        //说明：
        //1. String 的 matches 是整体匹配
        //2. 看看这个matches 底层
        /*
            String 的 matches
            public boolean matches(String regex) {
                    return Pattern.matches(regex, this);
            }
            Pattern
            public static boolean matches(String regex, CharSequence input) {
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(input);
                return m.matches();
            }

            Mather 类 match
            Attempts to match the entire region against the pattern
            public boolean matches() {
                return match(from, ENDANCHOR);
            }
        */
        if(content.matches("^[\\w-]+@([a-z]+\\.)+[a-zA-Z]+$")) {
            System.out.println("验证成功");
        } else {
            System.out.println("验证失败");
        }
    }
}
