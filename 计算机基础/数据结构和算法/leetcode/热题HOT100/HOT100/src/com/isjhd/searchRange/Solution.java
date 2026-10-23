package com.isjhd.searchRange;


import java.util.Arrays;

// 思路
// 可以退化成，找元素出现在数组中第一次出现的情况
// 找最后一个位置可以变为，找 target+1 在数组中第一次出现的情况

// 如何找元素出现在数组中第一次出现的情况
// 二分查找
// 大于等于的情况扔给right = mid - 1
// 小于的情况扔个left = mid + 1
// 不要考虑太多，就正常写法。只是把等于给了right那个if判断里面。
// 如果要找的数比任何数都要大，需要判断是否越界
class Solution {

    public int[] searchRange(int[] nums, int target) {

        if(nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int[] result = new int[2];
        int left = back(nums, target);
        if(left >= nums.length || nums[left] != target) {
            return new int[]{-1, -1};
        }
        result[0] = left;
        result[1] = back(nums, target + 1) - 1;

        return result;

    }

    private int back(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {

            int mid = (left + right) / 2;
            if(nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }

        return right;

    }




}

class Test {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {5,7,7,8,8,10};
        int[] ints = solution.searchRange(nums, 8);
        System.out.println(Arrays.toString(ints));
    }

}
