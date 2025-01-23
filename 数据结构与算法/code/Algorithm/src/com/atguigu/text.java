package com.atguigu;

import java.util.Arrays;

public class text {

    public static void main(String[] args) {
        int [] nums = {3,2,4};
        int target = 6;
        int[] ints = twoSum(nums, target);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] twoSum(int[] nums, int target) {
        for(int i = 0; i < nums.length;i++) {
            for(int j = 0; j < nums.length - 1 - i; j++) {
                if(nums[i] + nums[j+1+i] == target) {
                    int[] add = new int[2];
                    add[0] = i;
                    add[1] = j+1+i;
                    return add;
                }
            }
        }
        return null;
    }

}
