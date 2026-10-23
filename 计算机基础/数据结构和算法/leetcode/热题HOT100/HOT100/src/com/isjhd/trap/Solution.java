package com.isjhd.trap;

// 思路：
// 每次只判断一边;
// 每次只判断最短的那边的那一格能承载多少;
// 然后利用那一边最长的那个下标，减去当前的下标。
public class Solution {
    public int trap(int[] height) {

        int result = 0;
        int left = 0;
        int right = height.length - 1;
        int max_left = 0;
        int max_right = height.length - 1;
        while(left < right) {

            int temp = Math.min(height[left], height[right]);

            if(height[left] <= temp) {
                left++;
                if(left < right && height[max_left] > height[left]) {
                    result += height[max_left] - height[left];
                } else {
                    max_left = left;
                }
            }

            if(left < right && height[right] <= temp) {
                right--;
                if(left < right && height[max_right] > height[right]) {
                    result += height[max_right] - height[right];
                } else {
                    max_right = right;
                }
            }

        }
        return result;
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int trap = solution.trap(new int[]{2,0,2});
        System.out.println(trap);

    }
}