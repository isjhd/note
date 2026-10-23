package com.isjhd.maxProduct;

// 思路：
// 每次记录最大值和最小值，还有返回值
// 因为有负数的情况，所有最小值可能会变成最大值。

// 从 当前值 | 最大值 * 当前值 | 最小值 * 当前值
// 三者当中选择最小值和最大值
public class Solution {

    public int maxProduct(int[] nums) {

        int max_num = nums[0];
        int min_num = nums[0];
        int result = nums[0];
        for(int i = 1; i < nums.length; i++) {
            int temp = max_num;
            max_num = Math.max(Math.max(temp * nums[i], min_num * nums[i]), nums[i]);
            min_num = Math.min(Math.min(temp * nums[i], min_num * nums[i]), nums[i]);
            result = Math.max(Math.max(max_num, min_num), result);
        }

        return result;

    }
}

class Test{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-4,-3,-2};
        int i = solution.maxProduct(nums);
        System.out.println(i);
    }
}