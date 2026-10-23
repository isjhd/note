package com.isjhd.numSquares;


// 思路：
// 动态规划，从小到大遍历
// for循环一个一个查找
// 一个一个往回查找
// 用一个变量记录最小值
public class Solution {
    public int numSquares(int n) {

        int[] ints = new int[n + 1];
        for(int i = 1; i < ints.length; i++) {
            int temp = Integer.MAX_VALUE;
            for(int j = 1; j <= i; j++) {
                int num = j * j;
                if(num > i) {
                    break;
                } else {
                    temp = Math.min(temp, ints[i - num] + 1);
                }
            }
            ints[i] = temp;
        }

        return ints[n];
    }
}

class Test{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.numSquares(12);
        System.out.println(i);
    }
}