package com.isjhd.countBits;

//比特位计数

//给你一个整数 n ，对于 0 <= i <= n 中的每个 i ，计算其二进制表示中 1 的个数 ，
// 返回一个长度为 n + 1 的数组 ans 作为答案。

//示例 1：
//输入：n = 2
//输出：[0,1,1]
//解释：
//0 --> 0
//1 --> 1
//2 --> 10

import java.util.Arrays;

//示例 2：
//输入：n = 5
//输出：[0,1,1,2,1,2]
//解释：
//0 --> 0
//1 --> 1
//2 --> 10
//3 --> 11
//4 --> 100
//5 --> 101
public class Solution {
    public int[] countBits(int n) {
        int[] ints = new int[n + 1];
        for(int i = 1; i <= ints.length; i++) {
            ints[i] = ints[i >> 1] + (i & 1);
        }
        return ints;

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = solution.countBits(7);
        System.out.println(Arrays.toString(ints));
    }
}