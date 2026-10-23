package com.isjhd.threeSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 思路
// 从小到大排序
// 从两边向内判断
// num1 + num2 + num3 = 0; num1 + num2 = -num3;
// for循环 当前值 = -(left++ + right--)
// left = 当前值 + 1
// right = 最大值 - 1
// 注意：重复值没有判断的必要，可以直接跳过。
public class Solution {

    public static void main(String[] args) {
        int nums[] = {1,2,0,1,0,0,0,0};
        List<List<Integer>> lists = threeSum(nums);
        for(List<Integer> list : lists){
            for(int i : list) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> lists = new ArrayList<>();

        Arrays.sort(nums);
        for(int i = 0; i < nums.length; i++) {

            if(i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int mid = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right) {

                if(nums[left] + nums[right] > mid) {
                    right--;
                } else if (nums[left] + nums[right] < mid) {
                    left++;
                } else {
                    List<Integer> integers = new ArrayList<>(Arrays.asList(nums[left], nums[right], -mid));
                    lists.add(integers);

                    do {
                        left++;
                    } while(left < right && nums[left] == nums[left - 1]);

                    do {
                        right--;
                    } while(left < right && nums[right] == nums[right + 1]);

                }



            }

        }

        return lists;

    }

}
