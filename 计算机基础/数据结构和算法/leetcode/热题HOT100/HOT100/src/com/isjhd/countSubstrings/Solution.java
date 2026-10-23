package com.isjhd.countSubstrings;

//回文子串

//给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。

//回文字符串 是正着读和倒过来读一样的字符串。

//子字符串 是字符串中的由连续字符组成的一个序列。

//示例 1：
//输入：s = "abc"
//输出：3
//解释：三个回文子串: "a", "b", "c"

//示例 2：
//输入：s = "aaa"
//输出：6
//解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
public class Solution {
    public int countSubstrings(String s) {
        int result = s.length();

        char[] chars = s.toCharArray();

        for(int i = 0; i < chars.length; i++) {

            int k1 = i - 1;
            int k2 = i + 1;
            while(k1 >= 0 && k2 < chars.length && chars[k1--] == chars[k2++]) {
                result++;
            }

            k1 = i;
            k2 = i + 1;
            while(k1 >= 0 && k2 < chars.length && chars[k1--] == chars[k2++]) {
                result++;
            }

        }

        return result;

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.countSubstrings("aaa");
        System.out.println(i);
    }
}
