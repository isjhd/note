package com.isjhd.uniquePaths;


// 思路
// 01 | 01 | 01 | 01 | 01 | 01 | 01

// 01 | 02 | 03 | 04 | 05 | 06 | 07

// 01 | 03 | 06 | 10 | 15 | 21 | 28
// 一维数组即可解决
// 前一个值加上当前值
public class Solution {
    public int uniquePaths(int m, int n) {

        int[] ints = new int[m];
        for(int i = 0; i < m; i++) {
            ints[i] = 1;
        }

        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                ints[j] = ints[j - 1] + ints[j];
            }
        }

        return ints[m - 1];
    }

}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.uniquePaths(1, 7);
        System.out.println(i);
    }
}