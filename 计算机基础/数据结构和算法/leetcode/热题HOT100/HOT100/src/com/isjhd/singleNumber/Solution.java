package com.isjhd.singleNumber;

// 思路
// 使用 ^ 两个位相同为0，相异为1
public class Solution {
    public int singleNumber(int[] nums) {
        int i = 0;
        for(int num : nums) {
            i ^= num;
        }

        return i;
    }
}

class Tet{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2,2,1};
        int i = solution.singleNumber(nums);
        System.out.println(i);
    }
}