package com.isjhd.findMin;

// 思路：
// 二分查找
// 其实只需要判断右边即可
// 先找出中间值
// 如果小于右边，让right=中间值
// 如果大于右边，让left=中间值+1
public class Solution {

    public int findMin(int[] nums) {

        int n = nums.length - 1;
        int left = 0;
        int right = n;
        while(left < right) {
            int mid = (left + right) / 2;
            if(nums[mid] <= nums[n]) {
                right = mid;

            } else {
                left = mid + 1;
            }
        }
        return nums[right];
    }

}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findMin(new int[]{11,13,15,17});
    }
}