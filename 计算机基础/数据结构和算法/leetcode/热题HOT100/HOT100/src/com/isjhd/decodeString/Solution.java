package com.isjhd.decodeString;

import java.util.LinkedList;

// 思路
// 可以使用递归
// 全局变量记录进行到哪个下标了
// 不断循环，直到遇到"["即可向下递归，遇到"]"即可退出循环
class Solution {

    public String decodeString(String s) {

        return back(s).toString();

    }

    int k = 0;
    private StringBuilder back(String s) {

        if(k == s.length()) {
            return new StringBuilder();
        }

        StringBuilder stringBuilder = new StringBuilder();
        int nums = 0;
        while(k < s.length()) {

            char c = s.charAt(k);
            k++;

            if(c >= '0' && c <= '9') {
                nums = nums * 10 + (c - '0');
            } else if(c == '[') {
                StringBuilder back = back(s);
                for(int j = 0; j < nums; j++) {
                    stringBuilder.append(back);
                }
                nums = 0;
            } else if(c >= 'a' && c <= 'z') {
                stringBuilder.append(c);
            } else if(c == ']') {
                return stringBuilder;
            }
        }

        return stringBuilder;

    }

}


class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.decodeString("2[abc]3[cd]ef");
        System.out.println(s);
    }
}