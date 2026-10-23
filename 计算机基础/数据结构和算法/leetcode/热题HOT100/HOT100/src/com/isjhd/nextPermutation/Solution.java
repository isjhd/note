package com.isjhd.nextPermutation;


import java.util.Arrays;

// 思路
// 从右边开始，找到左边比右边小的，记录为left
// 再次从右边找，找到比left大的值，记录为right
// 将left和right的值互换
// 然后将left右边的值倒序排序
public class Solution {

    public static void main(String[] args) {
        int nums[] = {1,5,1};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void nextPermutation(int[] nums) {

        int left = nums.length - 2;
        while(left >= 0 && nums[left] >= nums[left + 1]) {
            left--;
        }

        int right = nums.length - 1;
        if(left >= 0) {

            while(right >= 0 && nums[right] <= nums[left]) {
                right--;
            }

            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

        }

        left++;
        right = nums.length - 1;
        while(left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }

    }
}