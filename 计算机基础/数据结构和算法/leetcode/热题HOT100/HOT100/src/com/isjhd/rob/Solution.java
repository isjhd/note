package com.isjhd.rob;

// 思路：
// 记录两个变量，一个left，一个right变量
// left为当前值，right要么是当前值，要么是下一个值
// right记录当前值或下一个值中的最大值，用temp变量记录right
// 然后让right记录当前值+下下值或本身的值中的最大值
// 然后让left=temp
// 这样就能知道三个值中，最大值和第二大值。如此循环下去。
public class Solution {
    public int rob(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        int left = nums[0];
        int right = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = right;
            right = Math.max(left, left + nums[i]);
            left = Math.max(temp, left);
        }

        return Math.max(left, right);

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 3, 1, 3, 100};
        int rob = solution.rob(nums);
        System.out.println(rob);
    }
}