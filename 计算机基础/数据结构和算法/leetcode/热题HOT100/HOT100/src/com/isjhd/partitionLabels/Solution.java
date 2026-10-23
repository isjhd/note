package com.isjhd.partitionLabels;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: isjhd
 * @date: 2025/11/25 21:36
 */

// 思路：
// new int[26]，存每个字母最后出现的下标
// 然后只需要 for循环 找到 字符串下标对应的值 和 上面数组值 相等的情况即可。
// 这段距离中，字符串下标对应的值最远的距离，即是下标和值相遇的时候。
public class Solution {
    public List<Integer> partitionLabels(String s) {

        int[] ints = new int[26];
        for(int i = 0; i < s.length(); i++) {
            ints[s.charAt(i) - 'a'] = i;
        }

        List<Integer> lists = new ArrayList<>();
        int temp = 0;
        int left = -1;
        for(int i = 0; i < s.length(); i++) {
            int size = ints[s.charAt(i) - 'a'];
            temp = Math.max(temp, size);
            if(temp == i) {
                lists.add(i - left);
                left = i;
            }
        }

        return lists;
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.partitionLabels("ababcbacadefegdehijhklij");
    }
}