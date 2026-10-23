package com.isjhd.coinChange;


import java.util.Arrays;

// 思路：
// 动态规划，从小到大依次遍历
// 可以把动态规划数组的值，设置为amount + 1，这是无论如何都达不到的最大值
// 这样只需要往回找，比对最小值即可。
public class Solution {
    public int coinChange(int[] coins, int amount) {

        int[] result = new int[amount + 1];
        for(int i = 1; i < result.length; i++) {

            int temp = result.length;
            for(int j = coins.length - 1; j >= 0; j--) {
                if(i - coins[j] >= 0) {
                    temp = Math.min(temp, result[i - coins[j]] + 1);
                }
            }
            result[i] = temp;
        }

        return result[amount] == result.length ? -1 : result[amount];

    }
}

class Test{
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        Solution solution = new Solution();
        int i = solution.coinChange(coins, 11);
        System.out.println(i);
    }
}