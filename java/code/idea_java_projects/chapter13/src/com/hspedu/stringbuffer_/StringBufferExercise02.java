package com.hspedu.stringbuffer_;
/*
 * @author  i-s-j-h-d
 * @version 1.0
 */


public class StringBufferExercise02 {
    public static void main(String[] args) {

        String s = "3456789.88";
        StringBuffer price = new StringBuffer(s);

        for (int i = price.indexOf(".") - 3; i > 0 ; i -= 3) {
            price.insert(i , ",");
        }

        System.out.println(price);
    }
}
