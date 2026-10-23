package com.isjhd.productExceptSelf;

// 思路
// 先从左向右乘，但是不乘自身
// 再从右向左乘，但是不乘自身
// 最后这两个数组上下相乘就行了
public class Solution {
    public int[] productExceptSelf(int[] nums) {

        int[] result = new int[nums.length];

        int temp = 1;
        for(int i = 0; i < nums.length; i++) {
            result[i] = temp;
            temp *= nums[i];
        }

        temp = 1;
        for(int i = nums.length - 1; i >= 0; i--) {
            result[i] *= temp;
            temp *= nums[i];
        }

        return result;

    }
}

class Test{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,2,3,4};
        int[] ints = solution.productExceptSelf(nums);
        for(int i : ints) {
            System.out.println(i);
        }
    }
}