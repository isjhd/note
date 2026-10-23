package com.isjhd.findUnsortedSubarray;

//最短无序连续子数组

//给你一个整数数组 nums ，你需要找出一个 连续子数组 ，
// 如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

//请你找出符合题意的 最短 子数组，并输出它的长度。

//示例 1：
//输入：nums = [2,6,4,8,10,9,15]
//输出：5
//解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。

//示例 2：
//输入：nums = [1,2,3,4]
//输出：0

import java.util.Arrays;

//示例 3：
//输入：nums = [1]
//输出：0
public class Solution {
    public int findUnsortedSubarray(int[] nums) {

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        int left = -1;
        int right = -1;

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] >= max) {
                max = nums[i];
            } else {
                right = i;
            }

            if(nums[nums.length - i - 1] <= min) {
                min = nums[nums.length - i - 1];
            } else {
                left = nums.length - i - 1;
            }
        }


        return left == -1 ? 0 : (right - left) + 1;

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.findUnsortedSubarray(new int[]{1,2,3,3,3});
        System.out.println(i);
    }
}