package com.isjhd.maxArea;


// 思路：
// 记录左右两边指针的最小值。
// 然后根据最小值，left++，right--。直到找到比最小值还要大的。
public class Solution {

    public static void main(String[] args) {
        int[] height = {1,2,4,3};
        int i = maxArea(height);
        System.out.println(i);
    }

    public static int maxArea(int[] height) {

        int result = 0;
        int left = 0;
        int right = height.length - 1;
        while(left < right) {

            int temp = Math.min(height[left], height[right]);
            result = Math.max(result, (right - left) * temp);

            while(left < right && temp >= height[left]) {
                left++;
            }

            while(left < right && temp >= height[right]) {
                right--;
            }


        }

        return result;

    }

}
