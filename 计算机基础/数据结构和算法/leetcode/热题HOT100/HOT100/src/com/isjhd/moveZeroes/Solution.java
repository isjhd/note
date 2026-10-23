package com.isjhd.moveZeroes;

// 思路：
// 只需一个变量记录第一个零的索引即可
// 剩下的只需一个个遍历，如果不是零则置换。
public class Solution {
    public void moveZeroes(int[] nums) {

        // 0, 1
        int left = 0;
        for(int i = 0; i < nums.length; i++) {

            if(nums[i] != 0) {
                int temp = nums[left];
                nums[left++] = nums[i];
                nums[i] = temp;
            }

        }

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {0, 1, 0, 3, 12};
        solution.moveZeroes(nums);
        for(int i : nums) {
            System.out.println(i);
        }
    }
}