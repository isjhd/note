package com.isjhd.lengthOfLongestSubstring;

import java.util.*;


//思路
// 使用map集合，key存单个字符，value存下标
// 开始遍历，遇到相同的put就算是更新
// 一个记录最终值，一个记录临时值

// 如果右边减去左边 比 临时值大
// 临时值+1，否则等于右边减去左边

// 临时变量与最后返回值进行最大值比较。
public class Solution {

    public static void main(String[] args) {
        String s = "pwwkew";
        int i = lengthOfLongestSubstring(s);
        System.out.println(i);
    }

    public static int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> hashMap = new HashMap<>();
        int result = 0;
        for(int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            if(hashMap.containsKey(c)) {
                result = Math.max(result, i - hashMap.get(c));
            } else {
                result++;
            }

            hashMap.put(c, i);

        }

        return result;

    }

}
