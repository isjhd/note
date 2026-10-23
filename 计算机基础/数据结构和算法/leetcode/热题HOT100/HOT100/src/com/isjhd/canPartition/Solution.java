package com.isjhd.canPartition;

// 思路：
// 只要其中部分值的和能等于总和的一半，即为true
// 可以使用动态规划，每个值只能使用一次，不能重复使用
public class Solution {
    public boolean canPartition(int[] nums) {

        int max_num = nums[0];
        int sum = 0;
        for(int i : nums) {
            sum += i;
            max_num = Math.max(max_num, i);
        }

        int half_sum = sum / 2;

        if(sum % 2 != 0 || max_num > half_sum) {
            return false;
        }

        if(max_num == half_sum) {
            return true;
        }

        boolean[] b = new boolean[half_sum + 1];
        b[0] = true;
        for(int i : nums) {
            for(int j = half_sum; j >= i; j--) {
                if(b[j - i]) {
                    b[j] = true;
                }
            }
        }

        return b[half_sum];

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean b = solution.canPartition(new int[]{1,2,3,4,5,6,7});
        System.out.println(b);
    }
}