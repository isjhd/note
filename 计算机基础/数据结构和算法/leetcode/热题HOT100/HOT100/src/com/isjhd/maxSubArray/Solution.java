package com.isjhd.maxSubArray;

// 思路
// 设置一个变量temp
// for循环
// temp要么等于当前这个数，要么等于当前这个数加上前一个temp
public class Solution {
    public int maxSubArray(int[] nums) {


        int temp = nums[0];
        int result = nums[0];
        for(int i = 1; i < nums.length; i++) {
            temp = Math.max(temp + nums[i], nums[i]);
            result = Math.max(result, temp);
        }

        return result;


    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        int i = solution.maxSubArray(nums);
        System.out.println(i);
    }
}