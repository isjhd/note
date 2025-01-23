package com.atguigu.util;

import javax.servlet.http.Cookie;

/* @author  i-s-j-h-d
 * @version 1.0 */
public class CookieUtils {

    /**
     * 查找指定名称的 Cookie 对象
     * @param name
     * @param cookies
     * @return
     */
    public static Cookie findCookie(String name, Cookie[] cookies){
        if (name == null || cookies == null || cookies.length == 0) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie;
            }
        }

        return null;
    }
}
