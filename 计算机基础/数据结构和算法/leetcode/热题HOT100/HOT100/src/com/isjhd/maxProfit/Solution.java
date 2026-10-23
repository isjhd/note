package com.isjhd.maxProfit;

// 思路
// 使用一个int变量记录最小的值即可
public class Solution {
    public int maxProfit(int[] prices) {

        int min = prices[0];
        int result = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] > min) {
                result = Math.max(result, prices[i] - min);
            } else {
                min = prices[i];
            }
        }

        return result;
    }
}

class Test{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] prices = {7,1,5,3,6,4};
        int i = solution.maxProfit(prices);
        System.out.println(i);
    }
}