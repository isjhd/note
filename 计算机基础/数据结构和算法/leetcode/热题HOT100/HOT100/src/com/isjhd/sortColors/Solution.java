package com.isjhd.sortColors;

import java.util.Arrays;

// 让所有值等于2。
// 之后如果小于等于1，则等于1。
// 在之后如果等于0，则等于0。
public class Solution {

    public void sortColors(int[] nums) {

        int red = 0;
        int white = 0;

        for(int i = 0; i < nums.length; i++) {

            int temp = nums[i];
            nums[i] = 2;

            if(temp <= 1) {
                nums[red++] = 1;
            }

            if(temp == 0) {
                nums[white++] = 0;
            }


        }

    }

}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2,0,2,1,1,0};
        solution.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}