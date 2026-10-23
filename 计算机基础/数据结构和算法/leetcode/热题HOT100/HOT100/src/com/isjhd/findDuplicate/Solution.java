package com.isjhd.findDuplicate;

// 思路：
// 快慢指针
public class Solution {
    public int findDuplicate(int[] nums) {

        int left = 0;
        int right = 0;

        do {
            left = nums[left];
            right = nums[nums[right]];
        }while(left != right);

        left = 0;
        while(left != right) {
            left = nums[left];
            right = nums[right];
        }

        return left;

    }
}

class Test{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,3,4,2,2};
        int i = solution.findDuplicate(nums);
        System.out.println(i);
    }
}