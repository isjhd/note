package com.isjhd.canJump;


// 思路
// 下标 + 值 = 长度
public class Solution {
    public boolean canJump(int[] nums) {

        int size = 0;
        for (int i = 0; i < nums.length; i++) {

            int temp = i + nums[i];
            size = Math.max(size, temp);

            if(size >= nums.length - 1) {
                return true;
            }

            if (size < i) {
                return false;
            }

        }

        return true;
    }
}

class Test {
    public static void main(String args[]) {
        Solution solution = new Solution();
        int nums[] = {2, 3, 1, 1, 4};
        boolean b = solution.canJump(nums);
        System.out.println(b);
    }
}