package com.isjhd.findAnagrams;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//思路：
// 定义int[] ints = new int[26], 两份
// for循环不断遍历两份数组是否相等，Arrays.equals()
// 第一个数组，持续减去头，添加尾部
public class Solution {
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> integers = new ArrayList<>();

        if(p.length() > s.length()) {
            return integers;
        }

        int[] int_s = new int[26];
        int[] int_p = new int[26];

        for(int i = 0; i < p.length(); i++) {
            int_s[s.charAt(i) - 'a']++;
            int_p[p.charAt(i) - 'a']++;
        }
        if(Arrays.equals(int_s, int_p)) {
            integers.add(0);
        }

        for(int i = p.length(); i < s.length(); i++) {

            int_s[s.charAt(i - p.length()) - 'a']--;
            int_s[s.charAt(i) - 'a']++;
            if(Arrays.equals(int_s, int_p)) {
                integers.add(i - p.length() + 1);
            }

        }

        return integers;

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<Integer> anagrams = solution.findAnagrams("cbaebabacd", "abc");
        for(int i : anagrams) {
            System.out.println(i);
        }
    }
}
