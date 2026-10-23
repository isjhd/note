package com.isjhd.majorityElement;

// 思路：
// 拿一个变量记录，如果相同，则加1，如果不同，则减1.
public class Solution {
    public int majorityElement(int[] nums) {
        int result = 0;
        int temp = 0;

        for(int num : nums) {
            if(temp == 0) {
                result = num;
            }

            temp += result == num ? 1 : -1;
        }

        return result;

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2,2,1,1,1,2,2};
        int i = solution.majorityElement(nums);
        System.out.println(i);
    }
}