package com.isjhd.jump;

/**
 * @description:
 * @author: isjhd
 * @date: 2025/11/24 21:31
 */

// 思路：
// for循环
// 下标+值 即可得出长度，
// 通过一个变量记录当前值，另一个变量记录记录这段区域的最大值
// 如果当前值 == 下标了，让这段区域的最大值==这个当前值。返回值++。
public class Solution {
    public int jump(int[] nums) {

        int result = 0;
        int max = 0;
        int now = 0;
        for(int i = 1; i < nums.length; i++) {

            max = Math.max(nums[i] + i, max);

            if(now == i) {
                result++;
                now = max;
            }

            if(now >= nums.length - 1) {
                return result;
            }

        }

        return result;

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int i = solution.jump(new int[]{2,9,6,5,7,0,7,2,7,9,3,2,2,5,7,8,1,6,6,6,3,5,2,2,6,3});
        System.out.println(i);
    }
}
