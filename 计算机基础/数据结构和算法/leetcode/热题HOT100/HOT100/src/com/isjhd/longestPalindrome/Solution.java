package com.isjhd.longestPalindrome;

// 思路
// 从单个字体向左右两边循环判断
// 有两种情况：单数和复数，所有需要判断字符串二倍的大小。
public class Solution {

    public static void main(String[] args) {
        String s = "bananas";
        String s1 = longestPalindrome(s);

        System.out.println(s1);
    }

    public static String longestPalindrome(String s) {

        char[] chars = s.toCharArray();
        int result_left = 0;
        int result_right = 0;
        for(int i = 0; i < s.length() * 2; i++) {
            int left = i / 2;
            int right = (i + 1) / 2;
            while(left >= 0 && right < s.length() && chars[left] == chars[right]) {
                left--;
                right++;
            }

            if(right - left - 2 > result_right + result_left) {
                result_left = left + 1;
                result_right = right - 1;
            }
        }

        return s.substring(result_left, result_right + 1);
    }
}
