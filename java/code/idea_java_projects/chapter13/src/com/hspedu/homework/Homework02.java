package com.hspedu.homework;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */

public class Homework02 {
    public static void main(String[] args) {

        try {
            enter("wj", "123456", "@.isjhd");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void enter(String name, String password, String mailbox) {
        StringBuffer s = new StringBuffer(mailbox);
        if (!(name.length() <= 4 && name.length() >= 2 && password.length() == 6 && isDigital(password)
                && s.indexOf("@") >= 0 && s.indexOf(".") >= 0 && mailbox.indexOf("@") < mailbox.indexOf("."))) {
            throw new RuntimeException("报错");
        }
        System.out.println("注册成功");
    }

    public static boolean isDigital(String password) {
        char [] chars = password.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            if(chars[i] < '0' || chars[i] > '9') {
                return false;
            }
        }
        return true;
    }
}
