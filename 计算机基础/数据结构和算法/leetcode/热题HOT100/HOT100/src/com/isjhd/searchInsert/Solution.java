package com.isjhd.searchInsert;

// 思路：
// 二分查找
// 没找到的话，如果left在前面，返回left;如果right在前面，返回right.
public class Solution {
    public int searchInsert(int[] nums, int target) {

        int left = 0;
        int right = nums.length;
        while(left < right) {

            int mid = (left + right) / 2;
            if(nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }

        }

        return right;

    }
}

class Test{
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.searchInsert(new int[]{1,3,5,6}, 7);
    }
}