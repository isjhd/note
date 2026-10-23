package com.isjhd.wordBreak;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 思路：
// 动态规划，设置一个boolean[]，大小是 s 参数的 s.length + 1
// 先找到集合中最大的一个单词长度
// 然后再这个范围两层for循环，外层循环 s.length，内层循环不断向内循环并比较。
// 如果在这个范围找到了，动态规划中数组下标置为true(必须开头为true，结尾才能为true)。
// i 为右边，j 为左边。
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {

        boolean[] bs = new boolean[s.length() + 1];
        int max = 0;
        for(String i : wordDict) {
            max = Math.max(max, i.length());
        }

        bs[0] = true;
        for(int i = 0; i < s.length(); i++) {
            if(bs[i] == true) {
                for (int j = Math.min(max + i, s.length() - 1); j >= i; j--) {
                    if (wordDict.contains(s.substring(i, j + 1))) {
                        bs[j + 1] = true;
                    }
                }
            }

        }

        return bs[s.length()];
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "leetcode";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");
        boolean b = solution.wordBreak(s, wordDict);
        System.out.println(b);
    }
}