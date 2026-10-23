package com.isjhd.numTrees;

//不同的二叉搜索树

//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树
// 有多少种？返回满足题意的二叉搜索树的种数。


public class Solution {
    public int numTrees(int n) {
        int[] m = new int[n+1];
        m[0] = 1;
        m[1] = 1;

        for(int i = 2; i <= n; i++) {
            for(int j = 1; j <= i; j++) {
                m[i] += m[j-1] * m[i-j];
            }
        }
        return m[n];
    }
}

class Test{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.numTrees(3);
        System.out.println(i);
    }
}