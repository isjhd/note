package com.isjhd.search;

// 思路：
// 二分查找
// 中间值根据nums[0]找到是左边还是右边

// 之后，如果是左边
// target 大于等于 left 并且 target 小于 中间值；right--;
// 如果是右边
// target 小于 right 并且 target 大于 中间值；left++;

// 注意：不仅需要对中间值进行左右边判断，还要对 target 进行左右边判断。
class Solution {
    public int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;

            if(nums[mid] == target) {
                return mid;
            }

            if(nums[mid] >= nums[0]) {
                if(target < nums[0] || target > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if(target >= nums[0] || target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }

        return -1;

    }
}
class Test{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3,1};
        System.out.println(solution.search(nums,1));
    }
}
